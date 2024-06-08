package ua.vholovetskyi.orderemail.messenger.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ua.vholovetskyi.orderemail.messenger.model.MessageEntity;
import ua.vholovetskyi.orderemail.messenger.model.MessageStatus;

import java.util.List;

public interface MessageRepository extends ElasticsearchRepository<MessageEntity, String> {

    List<MessageEntity> findAllByStatus(MessageStatus status);
//    Page<EmailEntity> findAll(Pageable pageable);
}
