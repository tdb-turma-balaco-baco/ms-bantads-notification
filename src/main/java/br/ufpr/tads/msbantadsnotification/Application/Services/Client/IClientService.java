package br.ufpr.tads.msbantadsnotification.Application.Services.Client;

import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientApprovedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.ClientRejectedEvent;
import br.ufpr.tads.msbantadsnotification.Application.Services.Client.Events.CreateClientFailEvent;

public interface IClientService {
    void sendEmailClientApproved(ClientApprovedEvent event);

    void sendEmailClientRejected(ClientRejectedEvent event);

    void sendEmailCreateClientFail(CreateClientFailEvent event);
}
