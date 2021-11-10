package am.onlinesite.onlinesitecommon.repasitory;

import am.onlinesite.onlinesitecommon.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository <Message, Long> {

        List<Message> findByToUser_Id(Long id);
}