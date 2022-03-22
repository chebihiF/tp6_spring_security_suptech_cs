package suptech.miage.tp6.sec;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static suptech.miage.tp6.sec.ApplicationUserPermission.*;
import static suptech.miage.tp6.sec.ApplicationUserPermission.TASK_WRITE;
import static suptech.miage.tp6.sec.ApplicationUserRole.ADMIN;
import static suptech.miage.tp6.sec.ApplicationUserRole.MANAGER;

@Configuration @EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index","/css/*","/js/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/tasks/**").hasAuthority(TASK_READ.getPermission())
                .antMatchers(HttpMethod.POST,"/api/v1/tasks/**").hasAuthority(TASK_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/api/v1/tasks/**").hasAuthority(TASK_DELETE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails admin = User
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails manager = User
                .builder()
                .username("manager")
                .password(passwordEncoder.encode("1234"))
//                .roles(MANAGER.name())
                .authorities(MANAGER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                manager
        );
    }
}
