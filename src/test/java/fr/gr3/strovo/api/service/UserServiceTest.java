package fr.gr3.strovo.api.service;


import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // GIVEN l'initialisation de Mockito avant chaque test
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        // WHEN un utilisateur est ajouté
        User user = new User();
        userService.addUser(user);
        // THEN la méthode save du UserRepository est appelée une fois avec cet utilisateur
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindUserByEmail() {
        // WHEN la recherche d'un utilisateur par email est effectuée
        String email = "test@iut.com";
        userService.findUserByEmail(email);
        // THEN la méthode findUserByEmail du UserRepository est appelée une fois avec cet email
        verify(userRepository, times(1)).findUserByEmail(email);
    }

    @Test
    public void testFindUserByEmailAndPassword() {
        // WHEN la recherche d'un utilisateur par email et mot de passe est effectuée
        String email = "test@iut.com";
        String password = "password";
        userService.findUserByEmailAndPassword(email, password);
        // THEN la méthode findUserByEmailAndPassword du UserRepository est appelée une fois avec ces identifiants
        verify(userRepository, times(1)).findUserByEmailAndPassword(email, password);
    }
}




