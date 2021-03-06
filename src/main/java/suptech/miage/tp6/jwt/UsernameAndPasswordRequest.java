package suptech.miage.tp6.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UsernameAndPasswordRequest {
    private String username;
    private String password;
}
