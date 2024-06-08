package ua.vholovetskyi.orderemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ua.vholovetskyi.orderemail.messenger.service.MessageProperties;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(MessageProperties.class)
public class OrderMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMessageApplication.class, args);
    }

}
