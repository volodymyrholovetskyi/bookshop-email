package ua.vholovetskyi.orderemail.messenger.service;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties("app.message")
public class MessageProperties {

    String sender;
    String dlqQueue;
}
