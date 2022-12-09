package br.edu.ifsc.BancoCentral.controller.transactions;

import br.edu.ifsc.BancoCentral.model.Request.Transactions.CreditTransactionRequest;
import br.edu.ifsc.BancoCentral.model.Request.Transactions.DebitTransactionRequest;
import br.edu.ifsc.BancoCentral.model.dto.InformacaoTransferenciaDto;
import br.edu.ifsc.BancoCentral.model.entity.Terminal;
import br.edu.ifsc.BancoCentral.model.entity.TransacaoCreditoEntity;
import br.edu.ifsc.BancoCentral.model.entity.TransacaoDebitoEntity;
import br.edu.ifsc.BancoCentral.model.filtro.FiltroCancelamento;
import br.edu.ifsc.BancoCentral.model.filtro.RelatorioFiltro;
import br.edu.ifsc.BancoCentral.repository.TerminalRepository;
import br.edu.ifsc.BancoCentral.repository.TransacaoCreditoRepository;
import br.edu.ifsc.BancoCentral.repository.TransacaoDebitoRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TrasacaoController {

    @Autowired
    TerminalRepository terminalRepository;

    @Autowired
    TransacaoCreditoRepository transacaoCreditoRepository;

    @Autowired
    TransacaoDebitoRepository transacaoDebitoRepository;

    @PostMapping(path = "/api/transacao/credito")
    public UUID transactionCredit(@RequestBody CreditTransactionRequest creditTransaction) {
        validaTerminal(creditTransaction.getIdTerminal());

        TransacaoCreditoEntity transacaoCreditoEntity = new TransacaoCreditoEntity();
        transacaoCreditoEntity.setCVV(creditTransaction.getCard().getCVV());
        transacaoCreditoEntity.setInstallments(creditTransaction.Installments);
        transacaoCreditoEntity.setNumero(transacaoCreditoEntity.getNumero());
        transacaoCreditoEntity.setValue(creditTransaction.Value);
        transacaoCreditoEntity.setData(LocalDateTime.now().toString());

        RestTemplate restTemplate = new RestTemplate();
        String url = "localhost:5000/api/credit-trasactions";

        String idBacen = restTemplate.postForObject(url, creditTransaction, String.class);

        if (!idBacen.isEmpty()) {
            transacaoCreditoEntity.setTransacaoOK(true);
            transacaoCreditoEntity.setIdBacen(UUID.fromString(idBacen));
        }
        else
            transacaoCreditoEntity.setTransacaoOK(false);

        transacaoCreditoRepository.save(transacaoCreditoEntity);

        return UUID.fromString(idBacen);
    }

    @PostMapping(path = "/api/transacao/debito")
    public UUID transactionDebit(@RequestBody DebitTransactionRequest debitTransaction, @RequestBody UUID id) {
        validaTerminal(id);

        TransacaoDebitoEntity transacaoDebitoEntity = new TransacaoDebitoEntity();
        transacaoDebitoEntity.setSenha(debitTransaction.getCard().getPassword());
        transacaoDebitoEntity.setNumero(debitTransaction.getCard().getNumber());
        transacaoDebitoEntity.setValor(debitTransaction.getValue());
        transacaoDebitoEntity.setData(LocalDateTime.now().toString());

        RestTemplate restTemplate = new RestTemplate();
        String url = "localhost:5000/api/debit-trasactions";

        String idBacen = restTemplate.postForObject(url, debitTransaction, String.class);

        if (!idBacen.isEmpty()) {
            transacaoDebitoEntity.setTransacaoOK(true);
            transacaoDebitoEntity.setIdBacen(UUID.fromString(idBacen));
        }
        else
            transacaoDebitoEntity.setTransacaoOK(false);

        transacaoDebitoRepository.save(transacaoDebitoEntity);

        return UUID.fromString(idBacen);
    }

    @GetMapping(path = "/api/transacao/listas")
    List<InformacaoTransferenciaDto> listarTransferencias(@RequestBody RelatorioFiltro filtro) {
        List<InformacaoTransferenciaDto> listTransferencia = new ArrayList<>();

        for (TransacaoDebitoEntity debito : transacaoDebitoRepository.findAll()) {
            InformacaoTransferenciaDto infoDto = new InformacaoTransferenciaDto();

            if (!validaFiltroDebito(filtro, debito))
                continue;

            infoDto.setData(debito.getData());
            infoDto.setValor(debito.getValor());
            infoDto.setNumero(debito.getNumero());
            infoDto.setSenha(debito.getSenha());

            listTransferencia.add(infoDto);
        }

        for (TransacaoCreditoEntity credito : transacaoCreditoRepository.findAll()) {
            InformacaoTransferenciaDto infoDto = new InformacaoTransferenciaDto();

            if (!validaFiltroCredito(filtro, credito))
                continue;

            infoDto.setCVV(credito.getCVV());
            infoDto.setInstallments(credito.getInstallments());
            infoDto.setData(credito.getData());
            infoDto.setValor(credito.getValue());
            infoDto.setNumero(credito.getNumero());

            listTransferencia.add(infoDto);
        }

        return listTransferencia;
    }

    public void cancelarTransferencia(@RequestBody FiltroCancelamento filtroCancelamento) {

        for (TransacaoDebitoEntity debito : transacaoDebitoRepository.findAll()) {
            if (filtroCancelamento.getIdBacen() == debito.getIdBacen() && filtroCancelamento.getIdTerminal() == debito.getIdTerminal()) {
                RestTemplate restTemplate = new RestTemplate();
                String url = "localhost:5000/api/{" + debito.getId().toString() + "}/cancel";
                String idBacen = restTemplate.getForObject(url, String.class);

                debito.setTransacaoCancelada(true);
                transacaoDebitoRepository.save(debito);
            }
        }

        for (TransacaoCreditoEntity credito : transacaoCreditoRepository.findAll()) {
            if (filtroCancelamento.getIdBacen() == credito.getIdBacen() && filtroCancelamento.getIdTerminal() == credito.getIdTerminal()) {
                RestTemplate restTemplate = new RestTemplate();
                String url = "localhost:5000/api/{" + credito.getId().toString() + "}/cancel";
                String idBacen = restTemplate.getForObject(url, String.class);

                credito.setTransacaoCancelada(true);
                transacaoCreditoRepository.save(credito);
            }
        }
    }

    private boolean validaFiltroDebito(RelatorioFiltro filtro, TransacaoDebitoEntity debito) {
        if (!filtro.getNumeroCartao().isEmpty() && !filtro.getNumeroCartao().equals(debito.getNumero()))
            return false;

        if (!filtro.getData().isEmpty()) {
            if (!comparaData(StringToLocalDateTime(filtro.getData()), StringToLocalDateTime(debito.getData())))
                return false;
        }

        if (filtro.getPreco() != debito.getValor())
            return false;

        if (!filtro.getIdTerminal().toString().isEmpty() && filtro.getIdTerminal().equals(debito.getIdTerminal()))
            return false;

        return true;
    }

    private boolean validaFiltroCredito(RelatorioFiltro filtro, TransacaoCreditoEntity credito) {
        if (!filtro.getData().isEmpty()) {
            if (!comparaData(StringToLocalDateTime(filtro.getData()), StringToLocalDateTime(credito.getData())))
                return false;
        }

        if (filtro.getPreco() != credito.getValue())
            return false;

        if (!filtro.getIdTerminal().toString().isEmpty() && filtro.getIdTerminal().equals(credito.getIdTerminal()))
            return false;

        if (!filtro.getNumeroCartao().isEmpty() && filtro.getNumeroCartao().equals(credito.getNumero()))
            return false;

        return true;
    }

    public void validaTerminal(UUID id) {
        Optional<Terminal> terminalOpt = terminalRepository.findById(id);

        if (terminalOpt.isEmpty())
            throw new ServiceException("Terminal não encontrado");

        Terminal terminal = terminalOpt.get();

        if (!terminal.getStatus())
            throw new ServiceException("Terminal está inativo");
    }

    private LocalDateTime StringToLocalDateTime(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateNow = LocalDateTime.parse(data, formatter);

        return dateNow;
    }

    private boolean comparaData(LocalDateTime dataFiltro, LocalDateTime dataTransacao) {
        if (dataFiltro.getYear() != dataTransacao.getYear())
            return false;

        if (dataFiltro.getMonth() != dataTransacao.getMonth())
            return false;

        if (dataFiltro.getDayOfMonth() != dataTransacao.getDayOfMonth())
            return false;

        return true;
    }
}
