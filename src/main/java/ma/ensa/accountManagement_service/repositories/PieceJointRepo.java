package ma.ensa.accountManagement_service.repositories;

import ma.ensa.accountManagement_service.entities.PieceJoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scripting.config.LangNamespaceHandler;

public interface PieceJointRepo extends JpaRepository<PieceJoint,Long> {
}
