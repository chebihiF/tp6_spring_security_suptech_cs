package suptech.miage.tp6.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import suptech.miage.tp6.sec.ApplicationUserRole;

import java.util.ArrayList;
import java.util.List;

import static suptech.miage.tp6.sec.ApplicationUserRole.ADMIN;
import static suptech.miage.tp6.sec.ApplicationUserRole.MANAGER;

@Repository("inMemoryRepo")
public class ApplicationUserRepositoryMemory implements ApplicationUserDao{

    private List<ApplicationUserDetail> users ;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserRepositoryMemory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users = new ArrayList<ApplicationUserDetail>();
        users.addAll(List.of(
                new ApplicationUserDetail(
                        passwordEncoder.encode("Admin"),
                        "Admin",
                        ADMIN.getGrantedAuthorities(),
                        false,
                        false,
                        false,
                        false
                ),
                new ApplicationUserDetail(
                        passwordEncoder.encode("1234"),
                        "Manager",
                        MANAGER.getGrantedAuthorities(),
                        false,
                        false,
                        false,
                        false
                ),
                new ApplicationUserDetail(
                        passwordEncoder.encode("SuperAdmin"),
                        "SuperAdmin",
                        ADMIN.getGrantedAuthorities(),
                        false,
                        false,
                        false,
                        false
                )
        ));
    }

    @Override
    public ApplicationUserDetail getUserByUsername(String username) {
        ApplicationUserDetail foundUser = users
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst().get();
        return foundUser;
    }
}
