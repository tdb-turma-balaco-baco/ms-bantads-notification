package br.ufpr.tads.msbantadsnotification.Application.Services.Client;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tads.msbantadsnotification.Application.Abstractions.IEmailService;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientApprovedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientRejectedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.CreateClientFailEvent;
import br.ufpr.tads.msbantadsnotification.Domain.Entities.Email;

@Service
public class ClientService implements IClientService {

    @Autowired
    IEmailService _emailService;

    @Override
    public void sendEmailClientApproved(ClientApprovedEvent event) {
        Email email = new Email();
        email.setDestinationEmail(event.getEmail());
        email.setSubject("BANTADS - Você foi aprovado!");
        
        StringBuilder template = new StringBuilder();
        template.append("Olá, {0}, deu tudo certo com seu processo de cadastro!\n\n ");
        template.append("Suas credencias são as seguintes: \n\n");
        template.append("Login: {1} \n");
        template.append("Senha: {2} \n\n");
        template.append("BANTADS - Obrigado");

        String emailText = MessageFormat.format(template.toString(), event.getName(), event.getEmail(), event.getPassword());

        email.setText(emailText);

        _emailService.sendEmail(email);        
    }

    @Override
    public void sendEmailClientRejected(ClientRejectedEvent event) {
        Email email = new Email();
        email.setDestinationEmail(event.getEmail());
        email.setSubject("BANTADS - Você foi negado!");
        
        StringBuilder template = new StringBuilder();
        template.append("Olá, {0}, infelizmente você foi recusado em nosso processo!\n\n ");
        template.append("Motivo: {1} \n\n");
        template.append("BANTADS - Agradecemos a compreensão");

        String emailText = MessageFormat.format(template.toString(), event.getName(), event.getRejectReason());
        email.setText(emailText);

        _emailService.sendEmail(email);   
        
    }

    @Override
    public void sendEmailCreateClientFail(CreateClientFailEvent event) {
        Email email = new Email();
        email.setDestinationEmail(event.getEmail());
        email.setSubject("BANTADS - Desculpe!");
        
        StringBuilder template = new StringBuilder();
        template.append("Olá, {0}, não foi possível concluir o seu cadastro!\n\n ");
        template.append("Entre em contato com nosso suporte para esclarecimentos \n\n");

        String emailText = MessageFormat.format(template.toString(), event.getName());
        email.setText(emailText);

        _emailService.sendEmail(email);   
        
    }

}
