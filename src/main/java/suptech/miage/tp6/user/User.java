package suptech.miage.tp6.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @Column(length = 30)
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @ManyToMany(mappedBy = "users")
    private List<Authority> authorities;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        authorities = new ArrayList<>();
    }
}
