// Déclaration du package et des classes nécessaires
package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.InterestPoint;
import fr.gr3.strovo.api.repository.InterestPointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

// Utilisation de l'extension Mockito pour JUnit 5
@ExtendWith(MockitoExtension.class)
public class InterestPointServiceTest {

    // Déclaration d'un mock pour le repository
    @Mock
    private InterestPointRepository interestPointRepository;

    // Déclaration de l'objet à tester
    private InterestPointService interestPointService;

    // Méthode exécutée avant chaque test pour initialiser les mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        interestPointService = new InterestPointService();
        interestPointService.setInterestPointRepository(interestPointRepository);
    }

    // Test pour la méthode addInterestPoint
    @Test
    public void testAddInterestPoint() {
        // Création d'un point d'intérêt
        InterestPoint interestPoint = new InterestPoint();
        // Appel de la méthode à tester
        interestPointService.addInterestPoint(interestPoint);
        // Vérification que la méthode insert du repository a été appelée une fois avec le point d'intérêt en argument
        verify(interestPointRepository, times(1)).insert(interestPoint);
    }

    // Test pour la méthode deleteInterestPoint
    @Test
    public void testDeleteInterestPoint() {
        // Appel de la méthode à tester
        interestPointService.deleteInterestPoint("1");
        // Vérification que la méthode deleteById du repository a été appelée une fois avec l'ID en argument
        verify(interestPointRepository, times(1)).deleteById("1");
    }

    // Test pour la méthode getInterestPointById
    @Test
    public void testGetInterestPointById() {
        // Création d'un point d'intérêt
        InterestPoint interestPoint = new InterestPoint();
        // Configuration du mock pour retourner le point d'intérêt lorsqu'il est recherché par son ID
        when(interestPointRepository.findById(anyString())).thenReturn(Optional.of(interestPoint));
        // Appel de la méthode à tester
        interestPointService.getInterestPointById("1");
        // Vérification que la méthode findById du repository a été appelée une fois avec l'ID en argument
        verify(interestPointRepository, times(1)).findById("1");
    }
    // Test pour le constructeur de copie
    @Test
    public void testCopyConstructor() {
        InterestPointService original = new InterestPointService();
        original.setInterestPointRepository(interestPointRepository);
        InterestPointService copy = new InterestPointService(original);
        assertEquals(original.getInterestPointRepository(), copy.getInterestPointRepository());
    }

    // Test pour la méthode getInterestPointById avec un ID non existant
    @Test
    public void testGetInterestPointByIdNotFound() {
        String id = "123";
        when(interestPointRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> interestPointService.getInterestPointById(id));
        String expectedMessage = "Cannot Find Interest Point by Id - " + id;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


}
