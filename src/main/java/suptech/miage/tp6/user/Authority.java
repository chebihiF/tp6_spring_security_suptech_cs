package suptech.miage.tp6.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Authority {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name ;
    @ManyToMany
    private List<User> users;
}
