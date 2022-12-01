package br.edu.ifsc.BancoCentral;

import br.edu.ifsc.BancoCentral.model.ModeloTeste;
import br.edu.ifsc.BancoCentral.repository.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClasseTeste {

    @Autowired
    TesteRepository testeRepository;

    @GetMapping(path = "/api/teste")
    public String teste() {

        ModeloTeste modeloTeste = new ModeloTeste("Daniel");
        testeRepository.save(modeloTeste);

        return "Conexão: on";
    }
}
