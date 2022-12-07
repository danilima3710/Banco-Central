package br.edu.ifsc.BancoCentral.controller;

import br.edu.ifsc.BancoCentral.model.Cartao;
import br.edu.ifsc.BancoCentral.model.Terminal;
import br.edu.ifsc.BancoCentral.repository.TerminalRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class TrasacaoController {

    @Autowired
    TerminalRepository terminalRepository;

    public void realizarTransavao(@RequestBody Cartao cartao, @RequestBody UUID id) {
        Optional<Terminal> terminalOpt = terminalRepository.findById(id);

        if (terminalOpt.isEmpty())
            throw new ServiceException("Terminal não encontrado");

        Terminal terminal = terminalOpt.get();

        if (!terminal.getStatus())
            throw new ServiceException("Terminal está inativo");
    }
}
