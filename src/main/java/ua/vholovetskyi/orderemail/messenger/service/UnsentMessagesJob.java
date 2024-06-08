package ua.vholovetskyi.orderemail.messenger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.orderemail.messenger.model.MessageStatus;

@Slf4j
@Component
@RequiredArgsConstructor
public class UnsentMessagesJob {

    private final MessageService messageService;

    /**
     * Task scheduler.
     * Search and tries to resend a message with a FAILED status.
     */
    @Transactional
    @Scheduled(cron = "${app.message.failed-cron}")
    public void run() {
        try {
            log.info("Starting Scheduled Task...");
            var emails = messageService.findAllByStatus(MessageStatus.FAILED);
            log.info("Found failed messages to resend: {}", emails.size());
            emails.forEach(messageService::sendMessage);
            log.info("Scheduled Task completed successfully!");
        } catch (Exception e) {
            log.error("Error executing scheduled task: {}", e.getMessage());
        }
    }
}
