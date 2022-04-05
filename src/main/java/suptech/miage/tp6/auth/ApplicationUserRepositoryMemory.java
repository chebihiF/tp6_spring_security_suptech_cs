package suptech.miage.tp6.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import suptech.miage.tp6.sec.ApplicationUserRole;

import java.util.List;

import static suptech.miage.tp6.sec.ApplicationUserRole.ADMIN;
import static suptech.miage.tp6.sec.ApplicationUserRole.MANAGER;

@Repository("inMemoryRepo")
public class ApplicationUserRepositoryMemory implements ApplicationUserDao{

    private List<ApplicationUserDetail> users ;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserRepositoryMemory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users.addAll(List.of(
                new ApplicationUserDetail(
                        passwordEncoder.encode("admin"),
                        "admin",
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUserDetail(
                        passwordEncoder.encode("1234"),
                        "manager",
                        MANAGER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUserDetail(
                        passwordEncoder.encode("superAdmin"),
                        "superAdmin",
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        ));
    }

    @Override
    public ApplicationUserDetail getUserByUsername(String username) {
        return users
                .stream()
                .filter(user->user.getUsername().equals(username))
                .findFirst().get();
    }
}
