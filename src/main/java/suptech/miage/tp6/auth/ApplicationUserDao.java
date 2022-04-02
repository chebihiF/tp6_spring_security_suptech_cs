package suptech.miage.tp6.auth;

public interface ApplicationUserDao {
    ApplicationUserDetail getUserByUsername(String username);
}
