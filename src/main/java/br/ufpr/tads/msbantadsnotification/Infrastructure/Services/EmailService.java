package br.ufpr.tads.msbantadsnotification.Infrastructure.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.ufpr.tads.msbantadsnotification.Application.Abstractions.IEmailService;
import br.ufpr.tads.msbantadsnotification.Domain.Entities.Email;

@Service
public class EmailService implements IEmailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getDestinationEmail());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        mailSender.send(message);
    }
    
}
