package suptech.miage.tp6.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>
{
    User findUserByUsername(String username);
}
