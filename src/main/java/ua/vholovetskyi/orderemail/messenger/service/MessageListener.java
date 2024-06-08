package ua.vholovetskyi.orderemail.messenger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.orderemail.commons.validator.MessageValidator;
import ua.vholovetskyi.orderemail.messenger.dto.MessageDto;

import static ua.vholovetskyi.orderemail.messenger.mapper.MessageFactory.createMessage;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-01
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MessageListener {

    private final MessageService messageService;
    private final MessageValidator validator;
    private final RabbitTemplate rabbitTemplate;
    private final MessageProperties properties;

    /**
     * Receives data from the main queue.
     * If it doesn't pass validation, we send it to the
     * dead message queue for next processing, as they will never be sent.
     *
     * @param message the object represent data.
     */
    @RabbitListener(queues = "${app.message.main-queue}", concurrency = "5")
    public void handleQueueMessage(MessageDto message) {
        if(!isValid(message)) {
            rabbitTemplate.convertAndSend(properties.getDlqQueue(), message);
            return;
        }
        messageService.saveAndSendMessage(createMessage(message, properties.getSender()));
    }

    private boolean isValid(MessageDto message) {
        return validator.validate(message);
    }
}
