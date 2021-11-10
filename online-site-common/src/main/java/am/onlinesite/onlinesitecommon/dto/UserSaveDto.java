package am.onlinesite.onlinesitecommon.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveDto {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Role role;
}
