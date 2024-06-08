package ua.vholovetskyi.orderemail.messenger.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.vholovetskyi.orderemail.messenger.model.MessageEntity;
import ua.vholovetskyi.orderemail.messenger.model.MessageStatus;
import ua.vholovetskyi.orderemail.messenger.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class EmailController {

    private final MessageService messageService;

    @GetMapping("/{status}")
    List<MessageEntity> findAllByStatus(@PathVariable MessageStatus status) {
        return messageService.findAllByStatus(status);
    }

//    @GetMapping("/{status}")
//    List<EmailEntity> findAll() {
//        return emailService.findAll();
//    }

    @DeleteMapping
    void deleteAll() {
        messageService.deleteAll();
    }
}
