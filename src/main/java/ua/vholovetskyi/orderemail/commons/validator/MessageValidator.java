package ua.vholovetskyi.orderemail.commons.validator;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.orderemail.messenger.dto.MessageDto;

@Component
@RequiredArgsConstructor
public class MessageValidator {

    private final Validator validator;

    /**
     * Validate date from the user ((incorrect mail format, empty subject or content)
     *
     * @param message object for validation
     * */
    public boolean validate(final MessageDto message) {
        return validator.validate(message).isEmpty();
    }
}
