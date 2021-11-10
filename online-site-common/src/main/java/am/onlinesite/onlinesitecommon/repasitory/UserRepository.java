package am.onlinesite.onlinesitecommon.repasitory;


import am.onlinesite.onlinesitecommon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByFirstName(String username);
    User findUserByUsername(String username);

}

