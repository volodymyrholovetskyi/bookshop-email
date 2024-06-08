package ua.vholovetskyi.orderemail.messenger.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.orderemail.messenger.model.MessageEntity;
import ua.vholovetskyi.orderemail.messenger.model.MessageStatus;
import ua.vholovetskyi.orderemail.messenger.repository.MessageRepository;
import ua.vholovetskyi.orderemail.messenger.service.mail.EmailSender;

import java.util.List;

import static ua.vholovetskyi.orderemail.commons.utils.MessageFormatters.EMPTY_MSG;
import static ua.vholovetskyi.orderemail.commons.utils.MessageFormatters.formatErrorMessage;
import static ua.vholovetskyi.orderemail.messenger.service.mail.EmailFactory.createEmail;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-02
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final EmailSender emailSender;
    private final MessageRepository emailRepo;

    /**
     * Find all emails by status.
     *
     * @param status find by criteria.
     * @return List<EmailEntity> or an emptyList if nothing is found.
     */
    public List<MessageEntity> findAllByStatus(MessageStatus status) {
        return emailRepo.findAllByStatus(status);
    }

    /**
     * Save message in DB.
     *
     * @param message object for storage in DB.
     */
    @Transactional
    public void saveAndSendMessage(MessageEntity message) {
        var savedMessage = emailRepo.save(message);
        sendMessage(savedMessage);
    }

    /**
     * Send a message to the recipient and changes status.
     * If an error occurs, the status changes to FAILED.
     *
     * @param message object to send.
     */
    public void sendMessage(MessageEntity message) {
        try {
            log.info("Start sending email...");
            emailSender.sendEmail(createEmail(message.getRecipient(), message.getSender()));
            updateFields(message, MessageStatus.SENT, EMPTY_MSG);
            log.info("Email sent successfully!");
        } catch (MailSendException err) {
            updateFields(message, MessageStatus.FAILED, formatErrorMessage(err.getClass().getName(), err.getMessage()));
            log.error("Error sending email. {}", err.getMessage());
        } catch (Exception err) {
            log.error("Error in sendMessage() method. {}", err.getMessage());
        }
    }

    private void updateFields(MessageEntity message, MessageStatus status, String errorMsg) {
        message.updateFields(status, errorMsg);
        emailRepo.save(message);
    }

    public void deleteAll() {
        emailRepo.deleteAll();
    }
}
