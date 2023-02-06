package br.ufpr.tads.msbantadsnotification.Domain.Entities;

public class Email {
    private String destinationEmail;
    private String subject;
    private String text;

    public Email() {
    }
    
    public Email(String destinationEmail, String subject, String text) {
        this.destinationEmail = destinationEmail;
        this.subject = subject;
        this.text = text;
    }
    public String getDestinationEmail() {
        return destinationEmail;
    }
    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    
}
