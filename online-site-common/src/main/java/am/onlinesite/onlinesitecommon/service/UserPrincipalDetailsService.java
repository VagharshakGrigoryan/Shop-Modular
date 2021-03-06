package am.onlinesite.onlinesitecommon.service;



import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return new CurrentUser(user.get());
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

}
