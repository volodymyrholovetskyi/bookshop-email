package ua.vholovetskyi.orderemail.messenger.mapper;

import ua.vholovetskyi.orderemail.messenger.model.MessageEntity;
import ua.vholovetskyi.orderemail.messenger.model.MessageStatus;
import ua.vholovetskyi.orderemail.messenger.model.Recipient;
import ua.vholovetskyi.orderemail.messenger.dto.MessageDto;

import java.time.Instant;

public class MessageFactory {

    public static MessageEntity createMessage(MessageDto message, String sender) {
        return MessageEntity.builder()
                .recipient(createRecipient(message))
                .sender(sender)
                .status(MessageStatus.PENDING)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    private static Recipient createRecipient(MessageDto message) {
        return Recipient.builder()
                .to(message.getTo())
                .subject(message.getSubject())
                .content(message.getContent())
                .build();
    }
}
