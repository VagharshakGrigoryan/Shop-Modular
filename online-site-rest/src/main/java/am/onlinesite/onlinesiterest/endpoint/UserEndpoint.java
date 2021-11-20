package am.onlinesite.onlinesiterest.endpoint;

import am.onlinesite.onlinesitecommon.dto.UserAuthDto;
import am.onlinesite.onlinesitecommon.dto.UserAuthResponseDto;
import am.onlinesite.onlinesitecommon.dto.UserDto;
import am.onlinesite.onlinesitecommon.dto.UserSaveDto;
import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import am.onlinesite.onlinesitecommon.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;


    @PostMapping("/users/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userRepository.findByEmail(userAuthDto.getEmail());
        if (byEmail.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }
    @GetMapping("/users")
    public List<UserDto> users(@AuthenticationPrincipal CurrentUser currentUser) {
        System.out.println("user with " + currentUser.getUser().getEmail() + " email get all users");
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(mapper.map(byId.get(), UserDto.class));
    }

    @PostMapping("/users")
    public UserDto user(@RequestBody UserSaveDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.map(userRepository.save(mapper.map(user, User.class)), UserDto.class);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> user(@PathVariable(name = "id") long id, @RequestBody UserSaveDto user) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        User userFromDb = byId.get();
        userFromDb.setUsername(user.getUsername());
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userFromDb.setRole(User.Role.USER);
        userFromDb.setEmail(user.getEmail());

        return ResponseEntity
                .ok()
                .body(mapper.map(userRepository.save(userFromDb), UserDto.class));
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteById(@PathVariable("id") long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        userRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
