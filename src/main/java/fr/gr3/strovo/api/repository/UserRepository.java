package fr.gr3.strovo.api.repository;
import fr.gr3.strovo.api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface décrivant un UserRepository.
 */
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    
}