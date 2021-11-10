package am.onlinesite.onlinesitecommon.service;


import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {

    User findByEmail(String email);

    void userAdd(User user, CurrentUser currentUser, Locale locale);

    void sendVerificEmail(User user, Locale locale) throws MessagingException;

    void verifyUser(String email, UUID token);

    List<User> findAllUsers();

    Page<User> userPage(PageRequest pageRequest);

}
