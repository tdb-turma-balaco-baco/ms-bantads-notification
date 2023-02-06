package br.ufpr.tads.msbantadsnotification.Application.Services.Manager;

import br.ufpr.tads.msbantadsnotification.Application.Services.Manager.Events.CreatedManagerEvent;

public interface IManagerService {
    void sendEmailCreatedManager(CreatedManagerEvent event);
}
