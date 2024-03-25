package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Token;
import fr.gr3.strovo.api.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TokenServiceTest
 */
@SpringBootTest(classes=TokenService.class)
class TokenServiceTest {

    private static final int USER_ID = 0;

    private static User user;

    @Autowired
    private TokenService tokenService;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setId(USER_ID);
    }

    @Test
    public void generateToken() {
        // GIVEN Token généré pour un utilisateur
        Token token = tokenService.generateToken(user, 0);
        // EXPECTED la valeur du token non null
        assertNotNull(token.getValue());
    }

    @Test
    public void isValidToken_WithValidToken() {
        // GIVEN Token généré pour un utilisateur qui expire dans 20 sec
        Token token = tokenService.generateToken(user, 20000);
        // EXPECTED token est valid
        assertTrue(tokenService.isValidToken(token));
    }

    @Test
    public void isValidToken_WithNotValidToken() throws InterruptedException {
        // GIVEN Token généré pour un utilisateur qui expire dans 1 sec
        int lifeTimeMs = 1000;
        Token token = tokenService.generateToken(user, lifeTimeMs);

        // WHEN nous attendons l'expiration du token
        Thread.sleep(lifeTimeMs);

        // THEN token n'est pas valide
        assertFalse(tokenService.isValidToken(token));
    }

    @Test
    public void getIdFromToken() {
        // GIVEN Token généré pour un utilisateur qui expire dans 20 sec
        Token token = tokenService.generateToken(user, 20000);

        // WHEN Récupère le user id du token
        int userId = tokenService.getUserIdFromToken(token);

        // EXPECTED userId est correct
        assertEquals(userId, USER_ID);
    }
}