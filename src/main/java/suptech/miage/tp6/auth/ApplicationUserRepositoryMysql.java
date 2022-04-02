package suptech.miage.tp6.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import suptech.miage.tp6.user.Authority;
import suptech.miage.tp6.user.AuthorityRepository;
import suptech.miage.tp6.user.User;
import suptech.miage.tp6.user.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ApplicationUserRepositoryMysql implements ApplicationUserDao{

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public ApplicationUserRepositoryMysql(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public ApplicationUserDetail getUserByUsername(String username) {

        User user = userRepository.findUserByUsername(username);
        List<Authority> authorities = authorityRepository.findAuthoritiesByUsersContains(user);
        Set<SimpleGrantedAuthority> grantedAuthorities = authorities
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());

        return new ApplicationUserDetail(
                user.getPassword(),
                user.getUsername(),
                grantedAuthorities,
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled()
        );
    }
}
