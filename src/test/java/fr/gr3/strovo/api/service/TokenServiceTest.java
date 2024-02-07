package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Token;
import fr.gr3.strovo.api.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

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
        // GIVEN generated token for user
        Token token = tokenService.generateToken(user, 0);
        // EXPECTED not null token value
        assertNotNull(token.getValue());
    }

    @Test
    public void isValidToken_WithValidToken() {
        // GIVEN generated token for user that expires in 20 sec
        Token token = tokenService.generateToken(user, 20000);
        // EXPECTED token is valid
        assertTrue(tokenService.isValidToken(token));
    }

    @Test
    public void isValidToken_WithNotValidToken() throws InterruptedException {
        // GIVEN generated token for user that expires in 1 second
        int lifeTimeSecond = 1;
        Token token = tokenService.generateToken(user, lifeTimeSecond * 1000);

        // WHEN we wait for token expiration
        TimeUnit.SECONDS.sleep(lifeTimeSecond);

        // THEN token is not valid
        assertFalse(tokenService.isValidToken(token));
    }

    @Test
    public void getIdFromToken() {
        // GIVEN generated token for user that expires in 20 sec
        Token token = tokenService.generateToken(user, 20000);

        // WHEN get user id from the token
        int userId = tokenService.getUserIdFromToken(token);

        // EXPECTED userId is correct
        assertEquals(userId, USER_ID);
    }
}