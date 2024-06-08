package ua.vholovetskyi.orderemail.messenger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.orderemail.messenger.dto.MessageDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class DLQMessageListener {

    /**
     * Receives data from the dead letter queue and logs the reason.
     *
     * @param message the object represent data.
     */
    @RabbitListener(queues = "${app.message.dlq-queue}")
    public void handleDLQMessage(MessageDto message) {
        log.error("The message did not pass validation! The message is: {}", message);
    }
}
