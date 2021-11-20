package am.onlinesite.onlinesitecommon.util;


import am.onlinesite.onlinesitecommon.model.Category;
import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.repasitory.CategoryRepository;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class  OnApplicationStartEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if (userRepository.findByEmail("admin@mail.com").isEmpty()) {
            userRepository.save(User.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(User.Role.ADMIN)
                    .username("admin")
                    .build());
            userRepository.save(User.builder()
                    .firstName("user")
                    .lastName("user")
                    .email("user@mail.com")
                    .password(passwordEncoder.encode("user"))
                    .role(User.Role.USER)
                    .username("user")
                    .build());
        }
        if (categoryRepository.findAll().isEmpty()) {

            categoryRepository.save(Category.builder()
                    .name("Book")
                    .type(Category.Type.BOOK)
                    .build());
            categoryRepository.save(Category.builder()
                    .name("CLOTHES")
                    .type(Category.Type.CLOTHES)
                    .build());
            categoryRepository.save(Category.builder()
                    .name("IT")
                    .type(Category.Type.IT)
                    .build());
            categoryRepository.save(Category.builder()
                    .name("Sport")
                    .type(Category.Type.SPORT)
                    .build());
            categoryRepository.save(Category.builder()
                    .name("School")
                    .type(Category.Type.SCHOOL)
                    .build());
            categoryRepository.save(Category.builder()
                    .name("For home")
                    .type(Category.Type.FOR_HOME)
                    .build());

    }
}}