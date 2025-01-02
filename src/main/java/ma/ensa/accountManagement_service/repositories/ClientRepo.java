package ma.ensa.accountManagement_service.repositories;

import ma.ensa.accountManagement_service.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client,Long> {
    public Optional<Client> findByEmail(String email);
}
