package br.ufpr.tads.msbantadsnotification.Application.Abstractions;

import br.ufpr.tads.msbantadsnotification.Domain.Entities.Email;

public interface IEmailService {
    void sendEmail(Email email);
}
