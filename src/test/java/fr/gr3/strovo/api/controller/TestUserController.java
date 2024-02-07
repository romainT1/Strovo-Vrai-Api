package fr.gr3.strovo.api.controller;
import fr.gr3.strovo.api.model.Token;
import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.service.TokenService;
import fr.gr3.strovo.api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestUserController {

    private static final String USER_EMAIL = "test@iut-rodez.fr";
    private static final String USER_PASSWORD = "password";
    private static final String TOKEN_VALUE = "ceciEstUnToken";

    private static User user1;
    private static User user2;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @BeforeAll
    public static void testInit() {
        user1 = new User(1, USER_EMAIL, USER_PASSWORD, "test", "test");
        user2 = new User(2, USER_EMAIL, USER_PASSWORD, "test2", "test2");
    }

    @Test
    public void testSignup() {
        when(userService.addUser(user1)).thenReturn(user1);

        // WHEN on s'inscrit avec un email et mot de passe valide
        ResponseEntity<User> response = (userController.signupUser(user1));

        // EXPECTED code retour 201 et l'utilisateur créé
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(user1, response.getBody());
    }

    @Test
    public void testSignupWithExistingEmail() {
        when(userService.findUserByEmail(USER_EMAIL)).thenReturn(user1);

        // WHEN on s'inscrit avec un email déjà utilisé
        ResponseEntity<User> response = (userController.signupUser(user2));

        // EXPECTED code retour 409
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testLoginWithValidEmailAndPassword() {
        int tokenLifetime = 86400000;

        when(userService.findUserByEmailAndPassword(USER_EMAIL,
                USER_PASSWORD)).thenReturn(user1);

        when(tokenService.generateToken(user1, tokenLifetime))
                .thenReturn(new Token(TOKEN_VALUE));

        // WHEN on s'identifie avec un email et mot de passe valide
        ResponseEntity<Token> result = userController.loginUser(USER_EMAIL,
                USER_PASSWORD);

        // EXPECTED code de retour 200 et les infos de l'utilisateur
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(TOKEN_VALUE, result.getBody().getValue());
    }

    @Test
    public void testLoginWithNotValidEmailAndPassword() {
        when(userService.findUserByEmailAndPassword(any(),
                any())).thenReturn(null);

        // WHEN on s'identifie avec un email et mot de passe non valide
        ResponseEntity<Token> result = userController.loginUser("","");

        // EXPECTED code de retour 403
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.FORBIDDEN);
    }
}
