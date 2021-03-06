package am.onlinesite.onlinesiteweb.controller;

import am.onlinesite.onlinesitecommon.model.Message;
import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import am.onlinesite.onlinesitecommon.service.MessageService;
import am.onlinesite.onlinesitecommon.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {


    private final UserServiceImpl userService;
    private final MessageService messageService;
    private final UserRepository userRepository;


    @GetMapping("/allMessage")
    public String getAllEmployees(ModelMap modelMap,@AuthenticationPrincipal CurrentUser currentUser) {
        List<Message> messages = messageService.findAllMessagesByToId(currentUser.getUser().getId());
        modelMap.addAttribute("messages", messages);
        List<User> userList = userService.findAllUsers();
        modelMap.addAttribute("userID", userList);
        return "showMessages";
    }


    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute Message message, @AuthenticationPrincipal CurrentUser currentUser) {
        message.setFromUser(currentUser.getUser());
        messageService.saveMessage(message);
        return "redirect:/allMessage";
    }

    @GetMapping("/deleteMessage/{id}")
    public String deleteMessage(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
        return "redirect:/allMessage";
    }
}


