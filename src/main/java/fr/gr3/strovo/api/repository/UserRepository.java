package fr.gr3.strovo.api.repository;
import fr.gr3.strovo.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface décrivant un UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Récupère un utilisateur à l'aide de l'email.
     *
     * @param email Adresse mail de l'utilisateur
     * @return L'utilisateur correspondant à l'email
     */
    @Query(value = "SELECT * FROM user WHERE email=?1", nativeQuery = true)
    User findUserByEmail(String email);
}
