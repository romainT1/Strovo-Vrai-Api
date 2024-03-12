package fr.gr3.strovo.api.service;


import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setPassword(passwordEncoder.encode("password"));
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
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        when(userRepository.findUserByEmail(email)).thenReturn(user);
        userService.findUserByEmailAndPassword(email, password);
        verify(userRepository, times(1)).findUserByEmail(email);
    }

    @Test
    public void testFindUserByEmailAndPassword_UserNotFound() {
        String email = "test@iut.com";
        String password = "password";
        when(userRepository.findUserByEmail(email)).thenReturn(null);
        assertNull(userService.findUserByEmailAndPassword(email, password));
        verify(userRepository, times(1)).findUserByEmail(email);
    }

    @Test
    public void testFindUserByEmailAndPassword_WrongPassword() {
        String email = "test@iut.com";
        String password = "password";
        String wrongPassword = "wrongPassword";
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        when(userRepository.findUserByEmail(email)).thenReturn(user);
        assertNull(userService.findUserByEmailAndPassword(email, wrongPassword));
        verify(userRepository, times(1)).findUserByEmail(email);
    }

}
