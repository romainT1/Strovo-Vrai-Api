package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service pour la gestion des utilisateurs.
 */
@Service
public class UserService {

    /** Injection de dépendance UserRepository. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Ajoute un utilisateur.
     *
     * @param user utilisateur à ajouter
     */
    public void addUser(@RequestBody final User user) {
        userRepository.save(user);
    }

    /**
     * Récupère un utilisateur à l'aide de l'email.
     *
     * @param email Adresse mail de l'utilisateur
     * @return l'utilisateur trouvé.
     */
    public User findUserByEmail(final String email) {
        return userRepository.findUserByEmail(email);
    }

    /**
     * Récupère un utilisateur à l'aide de l'email.
     *
     * @param email Adresse mail de l'utilisateur
     * @return l'utilisateur trouvé.
     */
    public User findUserByEmailAndPassword(final String email,
                                           final String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
