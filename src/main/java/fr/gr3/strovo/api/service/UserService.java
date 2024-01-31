package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.UserModel;
import fr.gr3.strovo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @param userModel utilisateur à ajouter
     */
    public void addUser(@RequestBody final UserModel userModel) {
        userRepository.save(userModel);
    }
}
