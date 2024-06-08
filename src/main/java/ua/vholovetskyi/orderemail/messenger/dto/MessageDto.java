package ua.vholovetskyi.orderemail.messenger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class MessageDto {

    @Email(message = "{email.invalidFormat}")
    @NotBlank(message = "{email.isBlank}")
    private String to;

    @NotBlank(message = "{email.isBlank}")
    private String subject;

    @NotBlank(message = "{email.isBlank}")
    private String content;
}
