package am.onlinesite.onlinesitecommon.service;


import am.onlinesite.onlinesitecommon.model.Message;
import am.onlinesite.onlinesitecommon.repasitory.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAllMessagesByToId(Long id) {
        return messageRepository.findByToUser_Id(id);
    }


    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
