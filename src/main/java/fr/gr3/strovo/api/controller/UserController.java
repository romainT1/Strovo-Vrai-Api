package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Token;
import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.service.TokenService;
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

    /** Service pour la gestion des tokens. */
    @Autowired
    private TokenService tokenService;

    /**
     * Ajoute un utilisateur.
     *
     * @param user utilisateur à ajouter
     * @return une réponse http :
     * <ul>
     *     <li>code 201 CREATED - si utilisateur ajouté</li>
     *     <li>code 409 CONFLICT - si email déjà utilisé</li>
     * </ul>
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@RequestBody final User user) {
        if (userService.findUserByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.addUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Renvoie un token d'authentification valide 24h
     *
     * @param email adresse mail de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return une réponse http :
     * <ul>
     *     <li>code 200 OK - si token généré</li>
     *     <li>code 403 FORBIDEN - si identifiants invalides</li>
     * </ul>
     */
    @GetMapping("/login")
    public ResponseEntity<Token> loginUser(
            @RequestParam() final String email,
            @RequestParam() final String password) {
        User user = userService.findUserByEmailAndPassword(email, password);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Token token = tokenService.generateToken(user);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
