package fr.gr3.strovo.api.controller;


import fr.gr3.strovo.api.model.InterestPoint;
import fr.gr3.strovo.api.service.InterestPointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TestInterestPointController {


    @InjectMocks
    private InterestPointController controller;


    @Mock
    private InterestPointService service;


    @BeforeEach
    public void setup() {
        // Initialise les mocks avant chaque test
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddInterestPoint() {
        // Crée un nouveau point d'intérêt
        InterestPoint point = new InterestPoint();


        // Appelle la méthode addInterestPoint du contrôleur
        ResponseEntity response = controller.addInterestPoint(point);


        // Vérifie que la méthode addInterestPoint du service a été appelée avec le bon argument
        verify(service).addInterestPoint(point);


        // Vérifie que le statut de la réponse est CREATED
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


//    @Test
//    public void testAddInterestPointNull() {
//        // Appelle la méthode addInterestPoint du contrôleur avec null comme argument
//        ResponseEntity response = controller.addInterestPoint(null);
//
//
//        // Vérifie que la méthode addInterestPoint du service n'a pas été appelée
//        verify(service, never()).addInterestPoint(any());
//
//
//        // Vérifie que le statut de la réponse est BAD_REQUEST
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }
//
//
//    @Test
//    public void testAddInterestPointException() {
//        // Fait en sorte que la méthode addInterestPoint du service lance une exception
//        doThrow(new RuntimeException()).when(service).addInterestPoint(any());
//
//
//        // Appelle la méthode addInterestPoint du contrôleur
//        ResponseEntity response = controller.addInterestPoint(new InterestPoint());
//
//
//        // Vérifie que le statut de la réponse est INTERNAL_SERVER_ERROR
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
}

