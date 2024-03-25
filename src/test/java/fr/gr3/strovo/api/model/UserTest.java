package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void testUser() {
        // GIVEN un utilisateur est créé avec des setters
        User user = new User();
        user.setId(1);
        user.setEmail("test@iut.com");
        user.setPassword("password");
        user.setLastname("Dupont");
        user.setFirstname("Jaques");

        // THEN les valeurs retournées correspondent à celles définies
        assertEquals(1, user.getId());
        assertEquals("test@iut.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("Dupont", user.getLastname());
        assertEquals("Jaques", user.getFirstname());
    }

    @Test
    void testUserConstructor() {
        // GIVEN un utilisateur est créé via le constructeur avec des paramètres spécifiques
        User user = new User(2, "test2@iut.com", "password2", "Durand", "Francois");

        // THEN les valeurs retournées correspondent à celles passées au constructeur
        assertEquals(2, user.getId());
        assertEquals("test2@iut.com", user.getEmail());
        assertEquals("password2", user.getPassword());
        assertEquals("Durand", user.getLastname());
        assertEquals("Francois", user.getFirstname());
    }

    @Test
    void testUserSetters() {
        // GIVEN un utilisateur est créé et modifié via des setters
        User user = new User();
        user.setId(3);
        user.setEmail("test3@iut.com");
        user.setPassword("password3");
        user.setLastname("Messi");
        user.setFirstname("Lionnel");

        // THEN les valeurs retournées correspondent aux nouvelles valeurs définies
        assertEquals(3, user.getId());
        assertEquals("test3@iut.com", user.getEmail());
        assertEquals("password3", user.getPassword());
        assertEquals("Messi", user.getLastname());
        assertEquals("Lionnel", user.getFirstname());
    }
}





