package am.onlinesite.onlinesiteweb.controller;

import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import am.onlinesite.onlinesitecommon.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping
    public String showRegisterForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute User user,
                                      @AuthenticationPrincipal CurrentUser currentUser, Locale locale, Errors errors, Model model,
                                      RedirectAttributes redirectAttributes) {
        User byEmail = userService.findByEmail(user.getEmail());
        if (byEmail != null) {
            return "redirect:/registration";
        }
        if (errors.hasErrors()) {
            return "registration";
        }

        userRepository.save(user);
        userService.userAdd(user, currentUser, locale);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "REGISTRATION SUCCESS");
        return "redirect:/login";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("email") String email,
                              @RequestParam("token") UUID token) {

        userService.verifyUser(email, token);
        return "verifyTemplate";
    }

}
