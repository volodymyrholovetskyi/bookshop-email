package ua.vholovetskyi.orderemail.commons.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue(@Value("${app.message.main-queue}") String emailQueue) {
        return new Queue(emailQueue, true);
    }

    @Bean
    public Queue deadLetterQueue(@Value("${app.message.dlq-queue}") String emailQueue) {
        return new Queue(emailQueue, true);
    }


}
