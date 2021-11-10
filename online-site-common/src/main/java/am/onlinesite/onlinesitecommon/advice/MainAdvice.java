package am.onlinesite.onlinesitecommon.advice;

import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MainAdvice {
    @ModelAttribute("currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return new User();
        }
        return currentUser.getUser();
    }
}
