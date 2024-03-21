package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTest {

    @Test
    public void testToken() {
        // Test du constructeur et de la méthode getValue
        String tokenValue = "testValue";
        Token token = new Token(tokenValue);
        assertEquals(tokenValue, token.getValue());

        // Test de la méthode setValue
        String newValue = "newValue";
        token.setValue(newValue);
        assertEquals(newValue, token.getValue());
    }
}
