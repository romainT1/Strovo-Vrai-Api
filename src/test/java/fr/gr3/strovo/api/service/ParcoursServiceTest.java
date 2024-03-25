// Déclaration du package et des classes nécessaires
package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.repository.ParcoursRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * classs de test de ParcoursService
 */
@ExtendWith(MockitoExtension.class)
public class ParcoursServiceTest {

    @Mock
    private ParcoursRepository parcoursRepository;

    @Mock
    private ParcoursService parcoursService;

    @BeforeEach
    public void setUp() {
        parcoursService = new ParcoursService();
        parcoursService.setParcoursRepository(parcoursRepository);
    }

    @Test
    public void testAddParcours() {
        // GIVEN un parcours à ajouter
        Parcours parcours = new Parcours();
        when(parcoursRepository.insert(parcours)).thenReturn(parcours);

        // WHEN on ajoute le parcours
        Parcours addedParcours = parcoursService.addParcours(parcours);

        // EXPECTED renvoie le parcours ajouté
        assertEquals(parcours, addedParcours);
    }

    @Test
    public void testGetParcoursById() {
        // GIVEN un identifant du parcour à récupérer
        String id = "p1";
        Parcours parcours = new Parcours();
        parcours.setId(id);

        when(parcoursRepository.findById(id)).thenReturn(Optional.of(parcours));

        // WHEN on recupère le parcours
        Parcours foundParcours = parcoursService.getParcoursById(id);

        // EXPECTED renvoie le parcours ajouté
        assertEquals(parcours, foundParcours);
    }

    @Test
    public void testGetParcours() {
        // GIVEN un identifant utilisateur pour lequel on veut récupérer
        //       les parcours associés.
        int userId = 1;
        Parcours parcours1 = new Parcours();
        Parcours parcours2 = new Parcours();
        parcours1.setUserId(userId);
        parcours2.setUserId(userId);

        List<Parcours> parcoursList = new ArrayList<>();
        parcoursList.add(parcours1);
        parcoursList.add(parcours2);

        when(parcoursRepository.findAllByUserId(userId)).thenReturn(parcoursList);

        // WHEN on recupère les parcours
        List<Parcours> foundParcoursList = parcoursService.getParcoursByUserId(userId);

        // EXPECTED renvoie le parcours ajouté
        assertEquals(parcoursList, foundParcoursList);
    }

    @Test
    public void testUpdateParcours() {
        // GIVEN un parcours que l'on souhaite mettre à jour
        String id = "p1";
        int userId = 1;

        Parcours newParcours = new Parcours();
        newParcours.setId(id);
        newParcours.setUserId(userId);
        newParcours.setName("NP");
        newParcours.setDescription("newParcours");

        when(parcoursRepository.save(newParcours)).thenReturn(newParcours);

        // WHEN on met à jour le parcours
        Parcours updatedParcours = parcoursService.updateParcours(newParcours);

        // EXPECTED renvoie le parcours ajouté
        assertEquals(newParcours, updatedParcours);
    }

}
