package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTest {

    @Test
    public void testToken() {
        // GIVEN un token est créé avec une valeur spécifique
        String tokenValue = "testValue";
        Token token = new Token(tokenValue);
        // THEN la valeur récupérée doit correspondre à la valeur définie lors de la création
        assertEquals(tokenValue, token.getValue());

        // WHEN la valeur du token est modifiée
        String newValue = "newValue";
        token.setValue(newValue);
        // THEN la nouvelle valeur doit correspondre à la valeur mise à jour
        assertEquals(newValue, token.getValue());
    }
}
