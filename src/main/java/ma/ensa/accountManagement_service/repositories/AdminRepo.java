package ma.ensa.accountManagement_service.repositories;

import ma.ensa.accountManagement_service.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Long> {
    Optional<Object> findByUsername(String admin);
}
