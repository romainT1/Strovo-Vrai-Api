package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.ParcoursService;
import fr.gr3.strovo.api.service.TokenService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ParcoursControllerTest {

    private final static int USER_ID = 1; 
    private final static String PARCOURS_ID = "1"; 
    private final static String TOKEN = "fake_token"; 
    private static Parcours parcours1;
    private static Parcours parcours2;

    @InjectMocks
    private ParcoursController parcoursController;
    
    @Mock
    private ParcoursService parcoursService;

    @Mock
    private TokenService tokenService;


    @BeforeAll
    public static void testInit() {
        parcours1 = new Parcours();
        parcours2 = new Parcours();
        parcours1.setName("p1");
        parcours2.setName("p2");
    }

    @Test
    public void testAddParcours() throws Exception {
        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.addParcours(parcours1)).thenReturn(parcours1);

        // WHEN on ajoute un parcours
        ResponseEntity<Parcours> response = parcoursController.addParcours(parcours1, TOKEN);

        // EXPECTED code retour 201 et parcours créé avec un identifiant
        //           et une référence de l'utilisateur
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(parcours1, response.getBody());
        Assertions.assertNotNull(parcours1.getId());
        Assertions.assertEquals(parcours1.getUserId(), USER_ID);
    }

    @Test
    public void testAddParcoursWithInvalidToken() throws Exception {
        // GIVEN un token de connexion invalide
        when(tokenService.isValidToken(any())).thenReturn(false);

        // WHEN on ajoute un parcours
        ResponseEntity<Parcours> response = parcoursController.addParcours(parcours1, TOKEN);

        // EXPECTED code retour 403
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }



    @Test
    public void testGetParcoursById() throws Exception {
        // GIVEN un parcours en base de donnée
        Parcours parcours = new Parcours();
        parcours.setId(PARCOURS_ID);
        parcours.setUserId(USER_ID);
        
        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.getParcoursById(any())).thenReturn(parcours);

        // WHEN on recherche un parcours par son identifiant
        ResponseEntity<Parcours> response = parcoursController.getParcoursById(PARCOURS_ID, TOKEN);

        // EXPECTED code retour 200 et parcours demandé
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(parcours, response.getBody());
    }

    @Test
    public void testGetParcoursByIdWithInvalidToken() throws Exception {
        // GIVEN un token de connexion invalide
        when(tokenService.isValidToken(any())).thenReturn(false);

        // WHEN on recherche un parcours
        ResponseEntity<Parcours> response = parcoursController.getParcoursById(PARCOURS_ID, TOKEN);

        // EXPECTED code retour 403
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testGetParcoursByIdButParcoursNotFound() throws Exception {
        // GIVEN un parcours en base de donnée
        Parcours parcours = new Parcours();
        parcours.setId(PARCOURS_ID);
        parcours.setUserId(USER_ID);
        
        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.getParcoursById(any())).thenReturn(null);

        // WHEN on recherche un parcours par son identifiant
        ResponseEntity<Parcours> response = parcoursController.getParcoursById(PARCOURS_ID, TOKEN);

        // EXPECTED code retour 204
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }



    @Test
    public void testGetParcours() {
        // GIVEN une liste de parcours pour un utilisateur en base de donnée
        Parcours parcours1 = new Parcours();
        Parcours parcours2 = new Parcours();
        parcours1.setUserId(USER_ID);
        parcours2.setUserId(USER_ID);

        List<Parcours> parcoursList = new ArrayList<>();
        parcoursList.add(parcours1);
        parcoursList.add(parcours2);
        
        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.getParcoursByUserId(USER_ID)).thenReturn(parcoursList);
        
        // WHEN on recherche la liste des parcours d'un utilisateur
        ResponseEntity<List<Parcours>> response = parcoursController.getParcours(TOKEN);

        // EXPECTED code retour 200 et liste des parcours trouvés
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(parcoursList, response.getBody());
    }

    @Test
    public void testGetParcoursWithInvalidToken() {
        // GIVEN un token de connexion invalide
        when(tokenService.isValidToken(any())).thenReturn(false);
        
        // WHEN on recherche la liste des parcours d'un utilisateur
        ResponseEntity<List<Parcours>> response = parcoursController.getParcours(TOKEN);

        // EXPECTED code retour 403
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testGetParcoursButNoParcoursFound() {
        // GIVEN aucun parcours pour un utiliasteur en base de donnée
        when(tokenService.isValidToken(any())).thenReturn(true);
        
        // WHEN on recherche la liste des parcours d'un utilisateur
        ResponseEntity<List<Parcours>> response = parcoursController.getParcours(TOKEN);

        // EXPECTED code retour 204
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }



    @Test
    public void testDeleteParcoursById() {
        // GIVEN un parcours d'un utilisateur en base de donnée
        Parcours parcours = new Parcours();
        String id = "p1";
        parcours.setId(id);
        parcours.setUserId(USER_ID);


        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.getParcoursById(id)).thenReturn(parcours);

        // WHEN on supprime un parcours de l' utilisateur
        ResponseEntity response = parcoursController.deleteParcours(id, TOKEN);
        
        // EXPECTED code retour 200
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testDeleteParcoursButInvalidToken() {
        // GIVEN un token de connexion invalide
        String id = "p1";

        when(tokenService.isValidToken(any())).thenReturn(false);

        // WHEN on supprime un parcours de l' utilisateur
        ResponseEntity response = parcoursController.deleteParcours(id, TOKEN);
        
        // EXPECTED code retour 204
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testdeleteParcoursButParcoursNotFound() {
        // GIVEN un identifiant de parcours introuvable en base de donnée
        String id = "p1";

        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.getParcoursById(id)).thenReturn(null);

        // WHEN on supprime un parcours de l' utilisateur
        ResponseEntity response = parcoursController.deleteParcours(id, TOKEN);
        
        // EXPECTED code retour 204
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }
    /*

    @Test
    public void testDeleteParcours() {
        // Créer un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description");
        InterestPoint[] interestPoints = {
            new InterestPoint("IP", "IPTest", new double[]{45.0, 48.2}),
            new InterestPoint("IP", "IPTest", new double[]{60.0, 71.3})
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


*/

}

