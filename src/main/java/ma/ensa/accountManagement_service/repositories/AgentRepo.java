package ma.ensa.accountManagement_service.repositories;

import ma.ensa.accountManagement_service.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepo extends JpaRepository<Agent,Long> {
    Optional<Agent> findAgentByEmail(String email);
}
