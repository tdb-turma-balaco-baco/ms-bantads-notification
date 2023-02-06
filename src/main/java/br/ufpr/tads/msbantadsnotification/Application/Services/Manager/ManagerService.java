package br.ufpr.tads.msbantadsnotification.Application.Services.Manager;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.tads.msbantadsnotification.Application.Abstractions.IEmailService;
import br.ufpr.tads.msbantadsnotification.Application.Services.Manager.Events.CreatedManagerEvent;
import br.ufpr.tads.msbantadsnotification.Domain.Entities.Email;

@Service
public class ManagerService implements IManagerService{

    @Autowired
    IEmailService _emailService;

    @Override
    public void sendEmailCreatedManager(CreatedManagerEvent event) {
        Email email = new Email();
        email.setDestinationEmail(event.getEmail());
        email.setSubject("BANTADS - Novo gerente");
        
        StringBuilder template = new StringBuilder();
        template.append("Olá, {0}, suas credencias de acesso foram criadas!\n\n ");
        template.append("Login: {1} \n");
        template.append("Senha: {2} \n\n");
        template.append("BANTADS - Obrigado");

        String emailText = MessageFormat.format(template.toString(), event.getName(), event.getEmail(), event.getPassword());

        email.setText(emailText);

        _emailService.sendEmail(email); 
    }
    
}
