package br.edu.ifsc.BancoCentral.controller.transactions;

import br.edu.ifsc.BancoCentral.model.Request.Transactions.CreditTransactionRequest;
import br.edu.ifsc.BancoCentral.model.Request.Transactions.DebitTransactionRequest;
import br.edu.ifsc.BancoCentral.model.entity.Terminal;
import br.edu.ifsc.BancoCentral.model.entity.TransacaoCreditoEntity;
import br.edu.ifsc.BancoCentral.model.entity.TransacaoDebitoEntity;
import br.edu.ifsc.BancoCentral.repository.TerminalRepository;
import br.edu.ifsc.BancoCentral.repository.TransacaoCreditoRepository;
import br.edu.ifsc.BancoCentral.repository.TransacaoDebitoRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@RestController
public class TrasacaoController {

    @Autowired
    TerminalRepository terminalRepository;

    @Autowired
    TransacaoCreditoRepository transacaoCreditoRepository;

    @Autowired
    TransacaoDebitoRepository transacaoCreditoEntity;

    @PostMapping(path = "/api/transacao/credito")
    public void transactionCredit(@RequestBody CreditTransactionRequest creditTransaction) {
        validaTerminal(creditTransaction.getIdTerminal());

        TransacaoCreditoEntity transacaoCreditoEntity = new TransacaoCreditoEntity();
        transacaoCreditoEntity.setCVV(creditTransaction.getCard().getCVV());
        transacaoCreditoEntity.setInstallments(creditTransaction.Installments);
        transacaoCreditoEntity.setNumero(transacaoCreditoEntity.getNumero());
        transacaoCreditoEntity.setValue(creditTransaction.Value);

        RestTemplate restTemplate = new RestTemplate();
        String url = "localhost:5000/api/credit-trasactions";

        String idBacen = restTemplate.getForObject(url, String.class);

        if (!idBacen.isEmpty()) {
            transacaoCreditoEntity.setTransacaoOK(true);
            transacaoCreditoEntity.setIdBacen(UUID.fromString(idBacen));
        }
        else
            transacaoCreditoEntity.setTransacaoOK(false);

        transacaoCreditoRepository.save(transacaoCreditoEntity);
    }

    @PostMapping(path = "/api/transacao/debito")
    public void transactionDebit(@RequestBody DebitTransactionRequest debitTransaction, @RequestBody UUID id) {
        validaTerminal(id);

        TransacaoDebitoEntity transacaoDebitoEntity = new TransacaoDebitoEntity();
        transacaoDebitoEntity.setSenha(debitTransaction.getCard().getPassword());
        transacaoDebitoEntity.setNumero(debitTransaction.getCard().getNumber());
        transacaoDebitoEntity.setValor(debitTransaction.getValue());

        RestTemplate restTemplate = new RestTemplate();
        String url = "localhost:5000/api/debit-trasactions";

        String idBacen = restTemplate.getForObject(url, String.class);

        if (!idBacen.isEmpty()) {
            transacaoDebitoEntity.setTransacaoOK(true);
            transacaoDebitoEntity.setIdBacen(UUID.fromString(idBacen));
        }
        else
            transacaoDebitoEntity.setTransacaoOK(false);

        transacaoCreditoEntity.save(transacaoDebitoEntity);
    }

    public void validaTerminal(UUID id) {
        Optional<Terminal> terminalOpt = terminalRepository.findById(id);

        if (terminalOpt.isEmpty())
            throw new ServiceException("Terminal não encontrado");

        Terminal terminal = terminalOpt.get();

        if (!terminal.getStatus())
            throw new ServiceException("Terminal está inativo");
    }
}
