package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Service pour la gestion des utilisateurs.
 */
@Service
public class UserService {

    /** Injection de dépendance UserRepository. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Crypteur de mots de passe.
     */
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    /**
     * Ajoute un utilisateur.
     *
     * @param user utilisateur à ajouter
     * @return L'utilisateur créé
     */
    public User addUser(@RequestBody final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
     * @param password Mot de passe de l'utilisateur
     * @return l'utilisateur trouvé.
     */
    public User findUserByEmailAndPassword(final String email,
                                           final String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && passwordEncoder.matches(
                password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
