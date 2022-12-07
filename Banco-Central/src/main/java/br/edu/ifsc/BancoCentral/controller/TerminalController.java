package br.edu.ifsc.BancoCentral.controller;

import br.edu.ifsc.BancoCentral.model.AlteraStatusTerminal;
import br.edu.ifsc.BancoCentral.model.entity.Terminal;
import br.edu.ifsc.BancoCentral.repository.TerminalRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TerminalController {

    @Autowired
    TerminalRepository terminalRepository;

    @PostMapping(path = "/api/terminal/cadastro")
    public void cadastrarTerminal(@RequestBody Terminal terminal) {
        try {
            validaTerminal(terminal);

            terminal = terminalRepository.save(terminal);

//            Email email = new Email();
//            email.enviarEmailCadastroTerminal(terminal);
        }
        catch (Exception e) {
            throw new RuntimeException(String.format("Erro ao cadastrar um terminal: %s", e.getMessage()));
        }
    }

    @PostMapping(path = "/api/terminal/alterarStatus")
    private void alterarStatusTerminal(@RequestBody AlteraStatusTerminal alteraStatus) {

        Optional<Terminal> terminalOpt = terminalRepository.findById(alteraStatus.getId());

        if (terminalOpt.isEmpty())
            throw new ServiceException("Não foi encontrado nenhum terminal com esse ID");

        Terminal terminal = terminalOpt.get();
        terminal.setStatus(alteraStatus.isStatus());

        terminalRepository.save(terminal);
    }

    private void validaTerminal(Terminal terminal) {
        if (terminal.getCnpj().isEmpty())
            throw new RuntimeException("CNPJ está vazio");

        if (terminal.getNomeEstabelecimento().isEmpty())
            throw new RuntimeException("Nome do estabelecimento está vazio");

        if (terminal.getEndereco().isEmpty())
            throw new RuntimeException("Endereço está vazio");

        if (terminal.getEmail().isEmpty())
            throw new RuntimeException("Email está vazio está vazio");
    }
}
