package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.ParcoursService;
import fr.gr3.strovo.api.service.TokenService;

import org.junit.jupiter.api.Assertions;
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

    @InjectMocks
    private ParcoursController parcoursController;
    
    @Mock
    private ParcoursService parcoursService;

    @Mock
    private TokenService tokenService;


    @Test
    public void testAddParcours() throws Exception {
        // GIVEN un parcours à ajouter
        Parcours parcours1 = new Parcours();
        parcours1.setName("p1");

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
        // GIVEN un token de connexion invalide et un parcours à ajouter
        Parcours parcours1 = new Parcours();
        parcours1.setName("p1");
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
        // GIVEN aucun parcours pour un utilisateur en base de donnée
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
        
        // EXPECTED code retour 403
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testDeleteParcoursButParcoursNotFound() {
        // GIVEN un identifiant de parcours introuvable en base de donnée
        String id = "p1";

        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.getParcoursById(id)).thenReturn(null);

        // WHEN on supprime un parcours de l'utilisateur
        ResponseEntity response = parcoursController.deleteParcours(id, TOKEN);
        
        // EXPECTED code retour 204
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }



    @Test
    public void testUpdatePacours() {
        // GIVEN un parcours à modifier pour un utilisateur en base de donnée
        String id = "p1";

        Parcours oldParcours = new Parcours();
        oldParcours.setId(id);
        oldParcours.setUserId(USER_ID);
        oldParcours.setName("OP");
        oldParcours.setDescription("oldParcours");

        Parcours newParcours = new Parcours();
        newParcours.setDescription("newParcours");

        Parcours editedParcours = oldParcours;
        editedParcours.setDescription(newParcours.getDescription());

        when(tokenService.isValidToken(any())).thenReturn(true);
        when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
        when(parcoursService.getParcoursById(id)).thenReturn(oldParcours);

        when(parcoursService.updateParcours(any())).thenReturn(editedParcours);

        // WHEN on modifie le parcours de l'utilisateur
        ResponseEntity<Parcours> response = parcoursController.updateParcours(id, newParcours, TOKEN);

        // EXPECTED code retour 200
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(editedParcours, response.getBody());
    }

    @Test
    public void testUpdatePacoursButInvalidToken() {
        // GIVEN un token de connexion invalide et un parcours à modifier
        //       pour un utilisateur en base de donnée
        String id = "1";

        Parcours newParcours = new Parcours();
        newParcours.setId(id);
        newParcours.setUserId(USER_ID);
        newParcours.setName("NP");
        newParcours.setDescription("newParcours");

        when(tokenService.isValidToken(any())).thenReturn(false);

        // WHEN on modifie le parcours de l'utilisateur
        ResponseEntity<Parcours> response = parcoursController.updateParcours(id, newParcours, TOKEN);

        // EXPECTED code retour 403
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testUpdatePacoursButParcoursNotFound() {
        // GIVEN un parcours inexistant à modifier pour un utilisateur
        //        en base de donnée
       String id = "1";

       Parcours newParcours = new Parcours();
       newParcours.setId(id);
       newParcours.setUserId(USER_ID);
       newParcours.setName("NP");
       newParcours.setDescription("newParcours");

       when(tokenService.isValidToken(any())).thenReturn(true);
       when(tokenService.getUserIdFromToken(any())).thenReturn(USER_ID);
       when(parcoursService.getParcoursById(id)).thenReturn(null);

       // WHEN on modifie le parcours de l'utilisateur
       ResponseEntity<Parcours> response = parcoursController.updateParcours(id, newParcours, TOKEN);

       // EXPECTED code retour 200
       Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
       Assertions.assertNull(response.getBody());
    }

}

