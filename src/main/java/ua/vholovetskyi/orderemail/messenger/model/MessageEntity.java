package ua.vholovetskyi.orderemail.messenger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Data
@Builder
@Document(indexName = "message")
public class MessageEntity {

    @Id
    private String id;

    @Field
    private Recipient recipient;

    @Field(type = FieldType.Text)
    private String sender;

    @Field(type = FieldType.Keyword)
    private MessageStatus status;

    @Field(type = FieldType.Text)
    private String errorMessage;

    @Field(type = FieldType.Integer)
    private int numberOfAttempts;

    @Field(type = FieldType.Date)
    private Instant createdAt;

    @Field(type = FieldType.Date)
    private Instant updatedAt;

    public void updateFields(MessageStatus newStatus, String errorMessage) {
        if (newStatus == null || status == newStatus) {
            return;
        }
        this.status = newStatus;
        this.errorMessage = errorMessage;
        ++numberOfAttempts;
        updatedAt = Instant.now();
    }
}
