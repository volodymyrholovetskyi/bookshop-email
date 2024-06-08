package ua.vholovetskyi.orderemail.messenger.service.mail;

public interface EmailSender {

    void sendEmail(EmailDto emailDto);
}
