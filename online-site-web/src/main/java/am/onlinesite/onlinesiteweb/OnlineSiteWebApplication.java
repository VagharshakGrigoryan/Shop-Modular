package am.onlinesite.onlinesiteweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan({"am.onlinesite.onlinesitecommon.*", "am.onlinesite.onlinesiteweb.*"})
@EnableJpaRepositories(basePackages = {"am.onlinesite.onlinesitecommon.*", "am.onlinesite.onlinesiteweb.*"})
@EntityScan("am.onlinesite.onlinesitecommon.*")
public class OnlineSiteWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSiteWebApplication.class, args);
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
