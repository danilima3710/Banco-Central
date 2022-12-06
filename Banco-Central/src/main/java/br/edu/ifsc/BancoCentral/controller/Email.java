package br.edu.ifsc.BancoCentral.controller;

import br.edu.ifsc.BancoCentral.model.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class Email {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${support.mail}")
//    private String supportMail;
//
//    public void enviarEmailCadastroTerminal(Terminal terminal) throws MessagingException {
//        MimeMessage mail = mailSender.createMimeMessage();
//
//        MimeMessageHelper message = new MimeMessageHelper(mail);
//        message.setSubject("Titulo");
//        message.setText("Esse é o corpo");
//        message.setFrom(supportMail);
//        message.setTo(terminal.getEmail());
//
//        mailSender.send(mail);
//    }
}
