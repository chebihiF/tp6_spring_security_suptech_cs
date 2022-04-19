package suptech.miage.tp6.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Authority {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name ;
    @ManyToMany
    @JoinTable(
            name = "role",
            joinColumns = @JoinColumn(name = "id_auth"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    @JsonIgnore
    private List<User> users;

    public Authority(String name) {
        this.name = name;
        users = new ArrayList<>();
    }
}
