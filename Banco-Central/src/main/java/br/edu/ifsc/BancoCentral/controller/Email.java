package br.edu.ifsc.BancoCentral.controller;

import br.edu.ifsc.BancoCentral.model.Terminal;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import java.util.UUID;

public class Email {

    public static void enviarEmailCadastroTerminal(Terminal terminal) {
//        String email = "mandarEmailPDSII@gmail.com";
//        String senha = "PDSII2022";

        String email = "vilson.l@aluno.ifsc.edu.br";
        String senha = "qwerty.BNU123";

        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setHostName("smtp.gmail.com");
        simpleEmail.setSmtpPort(465);
        simpleEmail.setAuthenticator(new DefaultAuthenticator(email, senha));
        simpleEmail.setSSLOnConnect(true);

        try {
            simpleEmail.setFrom(email);
            simpleEmail.setSubject("Cadastro do terminal");
            simpleEmail.setMsg(String.format("O código para a empresa %s é %s", terminal.getNomeEstabelecimento(), terminal.getId().toString()));
            simpleEmail.addTo(terminal.getEmail());
            simpleEmail.send();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
