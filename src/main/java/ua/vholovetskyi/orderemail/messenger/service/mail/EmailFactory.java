package ua.vholovetskyi.orderemail.messenger.service.mail;

import ua.vholovetskyi.orderemail.messenger.model.Recipient;

public class EmailFactory {

    public static EmailDto createEmail(Recipient recipient, String sender) {
        return EmailDto.builder()
                .to(recipient.getTo())
                .from(sender)
                .subject(recipient.getSubject())
                .content(recipient.getContent())
                .build();
    }
}
