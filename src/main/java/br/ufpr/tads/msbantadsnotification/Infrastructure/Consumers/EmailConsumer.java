package br.ufpr.tads.msbantadsnotification.Infrastructure.Consumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.ufpr.tads.msbantadsnotification.Application.Services.Client.IClientService;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientApprovedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientRejectedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.CreateClientFailEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Manager.IManagerService;
import br.ufpr.tads.msbantadsnotification.Application.Services.Manager.Events.CreatedManagerEvent;

@Component
@RabbitListener(queues = {"${notification.queue}"})
public class EmailConsumer {

    @Autowired
    IClientService _clientService;

    @Autowired
    IManagerService _managerService;
    
    @RabbitHandler
    public void receiveClientApproved(@Payload ClientApprovedEvent event){
        _clientService.sendEmailClientApproved(event);
    }

    @RabbitHandler
    public void receiveClientRejected(@Payload ClientRejectedEvent event){
        _clientService.sendEmailClientRejected(event);
    }
    
    @RabbitHandler
    public void receiveFailClient(@Payload CreateClientFailEvent event){
        _clientService.sendEmailCreateClientFail(event);
    }

    @RabbitHandler
    public void receiveCreatedManager(@Payload CreatedManagerEvent event){
        _managerService.sendEmailCreatedManager(event);
    }
}
