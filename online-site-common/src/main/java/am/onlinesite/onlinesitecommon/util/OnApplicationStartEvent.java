package am.onlinesite.onlinesitecommon.util;


import am.onlinesite.onlinesitecommon.model.Category;
import am.onlinesite.onlinesitecommon.model.Product;
import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.repasitory.CategoryRepository;
import am.onlinesite.onlinesitecommon.repasitory.ProductRepository;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import com.stripe.param.issuing.CardholderUpdateParams;
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
            Category company = categoryRepository.save(Category.builder()
                    .name("Itspace")
                    .type(Category.Type.BOOK)
                    .build());
            userRepository.save(User.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(User.Role.ADMIN)
                    .username("admin")
                    .build());
        }
    }
}