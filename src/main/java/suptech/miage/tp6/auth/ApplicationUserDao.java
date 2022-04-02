package suptech.miage.tp6.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface ApplicationUserDao {
    UserDetails getUserByUsername(String username);
}
