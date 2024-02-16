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
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        userService.addUser(user);
        verify(userRepository, times(1)).save(user);
    }


    @Test
    public void testFindUserByEmail() {
        String email = "test@iut.com";
        userService.findUserByEmail(email);
        verify(userRepository, times(1)).findUserByEmail(email);
    }


    @Test
    public void testFindUserByEmailAndPassword() {
        String email = "test@iut.com";
        String password = "password";
        userService.findUserByEmailAndPassword(email, password);
        verify(userRepository, times(1)).findUserByEmailAndPassword(email, password);
    }
}

