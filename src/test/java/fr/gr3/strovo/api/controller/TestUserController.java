package fr.gr3.strovo.api.controller;
import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class TestUserController {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    private static User testUser;

    @BeforeAll
    public static void init() {
        testUser = new User();
        testUser.setEmail("test@iut-rodez.fr");
        testUser.setPassword("hash_password");
        testUser.setLastname("testLastname");
        testUser.setFirstname("testFisrtname");
    }


    @Test
    public void testAddUser() throws Exception {
        Mockito.when(userService.addUser(testUser)).thenReturn(new User(1, "test@iut-rodez.fr", "hash_password",
                "testLastname", "testFisrtname"));

        ResponseEntity response = userController.addUser(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
