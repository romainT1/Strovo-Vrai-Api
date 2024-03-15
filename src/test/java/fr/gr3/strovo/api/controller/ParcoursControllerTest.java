package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.InterestPoint;
import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.ParcoursService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import fr.gr3.strovo.api.service.InterestPointService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParcoursControllerTest {

    @Mock
    private ParcoursService parcoursService;

    @Mock
    private InterestPointService interestPointService;

    @InjectMocks
    private ParcoursController parcoursController;

    @Test
    public void testAddParcours() throws Exception {
        // Créer un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description");

        // Appeler la méthode addParcours
        parcoursController.addParcours(parcours);

        // Vérifier que la méthode addParcours a été appelée avec le bon argument
        verify(parcoursService, times(1)).addParcours(parcours);
    }

    @Test
    public void testGetParcoursByParcoursId() {
        // Créer un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description");

        // Configurer le mock pour retourner le parcours lorsqu'il est recherché par son ID
        when(parcoursService.getParcoursById("123")).thenReturn(parcours);

        // Appeler la méthode getParcoursByParcoursId
        ResponseEntity<Parcours> response = parcoursController.getParcoursByParcoursId("123");

        // Vérifier que le statut de la réponse est OK et que le corps de la réponse est le parcours attendu
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(parcours, response.getBody());
    }

    @Test
    public void testDeleteParcours() {
        // Créer un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description");
        InterestPoint[] interestPoints = { 
            new InterestPoint("1", "IP", "IPTest", new double[]{45.0, 48.2}),
            new InterestPoint("2", "IP", "IPTest", new double[]{60.0, 71.3}),
        };
        parcours.setInterestPoints(interestPoints);

        // Configurer le mock pour retourner le parcours lorsqu'il est recherché par son ID
        when(parcoursService.getParcoursById("123")).thenReturn(parcours);

        // Appeler la méthode deleteParcours
        ResponseEntity<Void> response = parcoursController.deleteParcours("123");

        // Vérifier que le statut de la réponse est No Content
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Vérifier que la méthode deleteParcours a été appelée avec le bon argument
        verify(parcoursService, times(1)).deleteParcours("123");
    }


    @Test
    public void testUpdateParcours() {
        // Créer un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description");

        // Appeler la méthode updateParcours
        ResponseEntity<Void> response = parcoursController.updateParcours(parcours);

        // Vérifier que le statut de la réponse est OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Vérifier que la méthode updateParcours a été appelée avec le bon argument
        verify(parcoursService, times(1)).updateParcours(parcours);
    }

    @Test
    public void testGetParcoursByUserIdWithoutFilters() {
        // Créer un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description");

        // Configurer le mock pour retourner une liste de parcours lorsqu'ils sont recherchés par l'ID de l'utilisateur sans filtre
        when(parcoursService.getParcoursByUserIdAndFilters(1, null)).thenReturn(Arrays.asList(parcours));

        // Appeler la méthode getParcoursByUserId avec tous les paramètres à null
        ResponseEntity<List<Parcours>> response = parcoursController.getParcoursByUserId(1, null, null, null);

        // Vérifier que le statut de la réponse est OK et que le corps de la réponse est la liste de parcours attendue
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Arrays.asList(parcours), response.getBody());
    }

    @Test
    public void testGetParcoursByUserIdWithFilters() {
        // Créer un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description");

        // Configurer le mock pour retourner une liste de parcours lorsqu'ils sont recherchés par l'ID de l'utilisateur avec un filtre
        when(parcoursService.getParcoursByUserIdAndFilters(eq(1), any(Filter.class))).thenReturn(Arrays.asList(parcours));

        // Appeler la méthode getParcoursByUserId avec des valeurs non nulles pour nom, dateDebut et dateFin
        ResponseEntity<List<Parcours>> response = parcoursController.getParcoursByUserId(1, "nom", "dateDebut", "dateFin");

        // Vérifier que le statut de la réponse est OK et que le corps de la réponse est la liste de parcours attendue
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Arrays.asList(parcours), response.getBody());

        // Vérifier que la méthode getParcoursByUserIdAndFilters a été appelée avec le bon Filter
        ArgumentCaptor<Filter> argumentCaptor = ArgumentCaptor.forClass(Filter.class);
        verify(parcoursService, times(1)).getParcoursByUserIdAndFilters(eq(1), argumentCaptor.capture());
        Filter filter = argumentCaptor.getValue();
        Assertions.assertEquals("nom", filter.getNameParcours());
        Assertions.assertEquals("dateDebut", filter.getStartDate());
        Assertions.assertEquals("dateFin", filter.getEndDate());
    }




}

