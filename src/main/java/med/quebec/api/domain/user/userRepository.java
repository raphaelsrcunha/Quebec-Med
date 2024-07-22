package med.quebec.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface userRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
