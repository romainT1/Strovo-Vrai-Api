package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.UserModel;
import fr.gr3.strovo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * @param userModel utilisateur à ajouter
     * @return une réponse http contenant un code 200
     */
    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
