package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller pour la gestion des utilisateurs.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    /** Service pour la gestion des utilisateurs. */
    @Autowired
    private UserService userService;

    /**
     * Ajoute un utilisateur.
     *
     * @param user utilisateur à ajouter
     * @return une réponse http :
     * <ul>
     *     <li>code 201 CREATED - si utilisateur ajouté</li>
     *     <li>code 409 CONFLICT - si email déjà utilisé</li>
     *     <li>code 400 BAD_REQUEST - si utilisateur ajouté</li>
     * </ul>
     */
    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody final User user) {
        if (userService.findUserByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.addUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Récupère un utilisateur à l'aide de l'email.
     *
     * @param email Adresse mail de l'utilisateur à rechercher
     * @return une réponse http contenant un code 200 et l'utilisateur trouvé
     */
    @GetMapping
    public ResponseEntity<User> findUserByEmail(
            @RequestParam() final String email) {
        User user = userService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
