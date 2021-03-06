package am.onlinesite.onlinesiteweb.controller;


import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import am.onlinesite.onlinesitecommon.service.MailService;
import am.onlinesite.onlinesitecommon.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @GetMapping
    public String userPage(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "3") int size,
                           @RequestParam(value = "orderBy", defaultValue = "title") String orderBy,
                           @RequestParam(value = "order", defaultValue = "ASC") String order,
                           ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {

                Sort.by(Sort.Order.desc(orderBy));

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<User> userList = userService.userPage(pageRequest);

        int totalPages = userList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        modelMap.addAttribute("users", userList);
        log.info("User with {} username opened user page, user.size = {}",
                currentUser.getUser().getUsername(),
                userList.getSize());
        return "user";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userRepository.deleteById(id);
        String msg = "User was removed";
        return "redirect:/?msg=" + msg;
    }

    @GetMapping("/forgotPassword")
    public String forgotPass(@RequestParam("email") String email, Locale locale) throws MessagingException {
        Optional<User> username = userRepository.findByEmail(email);
        if (username.isPresent() && username.get().isEmailVerified()) {
            User user = username.get();
            UUID token = UUID.randomUUID();
            user.setToken(token);
            userRepository.save(user);
            String link = "http://localhost:8080/user/forgotPassword/reset?email="
                    + user.getUsername() + "&token=" + token;
            mailService.sendHtmlEmail(user.getEmail(), "RESET password",user,link,
                    "mailReset",locale );
        }
        return "redirect:/";
    }

    @GetMapping("/forgotPassword/reset")
    public String forgotPassReset(ModelMap modelMap, @RequestParam("email") String email, @RequestParam("token") UUID token) {
        Optional<User> username = userRepository.findByUsername(email);
        if (username.isPresent() && username.get().getToken().equals(token)) {
            modelMap.addAttribute("email", username.get().getUsername());
            modelMap.addAttribute("token", username.get().getToken());
            return "changePassword";
        }
        return "redirect:/";
    }

    @PostMapping("/forgotPassword/change")
    public String changePassword(@RequestParam("email") String email, @RequestParam("token") UUID token,
                                 @RequestParam("password") String password,
                                 @RequestParam("repeatPassword") String repeatPassword) {
        Optional<User> username = userRepository.findByUsername(email);
        if (username.isPresent()) {
            User user = username.get();
            if (user.getToken().equals(token) && password.equals(repeatPassword)) {
                user.setToken(token);
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                return "redirect:/?msg=Your password changed!";
            }
        }
        return "redirect:/";
    }
}
