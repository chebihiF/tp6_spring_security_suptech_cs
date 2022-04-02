package suptech.miage.tp6.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class AuthConfig {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthConfig(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initAuth(){
        return args -> {
          User user_admin = userRepository.save
                  (new User("admin",passwordEncoder.encode("admin")));
          User user_manager = userRepository.save
                (new User("manager",passwordEncoder.encode("1234")));

          Authority authority_write = authorityRepository.save(new Authority("task:write"));
          Authority authority_read = authorityRepository.save(new Authority("task:read"));
          Authority authority_delete = authorityRepository.save(new Authority("task:delete"));

          authority_read.getUsers().add(user_admin);
          authority_read.getUsers().add(user_manager);
          authorityRepository.save(authority_read);

          authority_write.getUsers().add(user_admin);
          authority_write.getUsers().add(user_manager);
          authorityRepository.save(authority_write);

          authority_delete.getUsers().add(user_admin);
          authorityRepository.save(authority_delete);

          //List<Authority> authorities = authorityRepository.findAuthoritiesByUsersContains(user_admin);
        };
    }
}
