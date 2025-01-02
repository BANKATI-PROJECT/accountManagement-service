package ma.ensa.accountManagement_service.repositories;

import ma.ensa.accountManagement_service.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepo extends JpaRepository<UserApp,Long> {
    Optional<UserApp> findByUsername(String username);
}
