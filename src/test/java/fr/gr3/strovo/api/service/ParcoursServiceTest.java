// Déclaration du package et des classes nécessaires
package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.repository.ParcoursRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

// Utilisation de l'extension Mockito pour JUnit 5
@ExtendWith(MockitoExtension.class)
public class ParcoursServiceTest {

    // Déclaration d'un mock pour le repository
    @Mock
    private ParcoursRepository parcoursRepository;

    // Déclaration de l'objet à tester
    private ParcoursService parcoursService;

    // Méthode exécutée avant chaque test pour initialiser les mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        parcoursService = new ParcoursService();
        parcoursService.setParcoursRepository(parcoursRepository);
    }

    // Test pour la méthode addParcours
    @Test
    public void testAddParcours() {
        // Création d'un parcours
        Parcours parcours = new Parcours();
        // Appel de la méthode à tester
        parcoursService.addParcours(parcours);
        // Vérification que la méthode insert du repository a été appelée une fois avec le parcours en argument
        verify(parcoursRepository, times(1)).insert(parcours);
    }

    // Test pour la méthode getParcoursById
    @Test
    public void testGetParcoursById() {
        // Création d'un parcours
        Parcours parcours = new Parcours();
        // Configuration du mock pour retourner le parcours lorsqu'il est recherché par son ID
        when(parcoursRepository.findById(anyString())).thenReturn(Optional.of(parcours));
        // Appel de la méthode à tester
        parcoursService.getParcoursById("1");
        // Vérification que la méthode findById du repository a été appelée une fois avec l'ID en argument
        verify(parcoursRepository, times(1)).findById("1");
    }

    // Test pour la méthode getParcoursByUserIdAndFilters
    @Test
    public void testGetParcoursByUserIdAndFilters() {
        // Création d'un filtre
        Filter filter = new Filter("nom", "dateDebut", "dateFin");
        // Appel de la méthode à tester
        parcoursService.getParcoursByUserIdAndFilters(1, filter);
        // Vérification que la méthode findAllByUserIdAndFilters du repository a été appelée une fois avec les arguments attendus
        verify(parcoursRepository, times(1)).findAllByUserIdAndFilters(1, filter);
    }

    // Test pour la méthode deleteParcours
    @Test
    public void testDeleteParcours() {
        // Appel de la méthode à tester
        parcoursService.deleteParcours("1");
        // Vérification que la méthode deleteById du repository a été appelée une fois avec l'ID en argument
        verify(parcoursRepository, times(1)).deleteById("1");
    }

    // Test pour la méthode updateParcours
    @Test
    public void testUpdateParcours() {
        // Création d'un parcours
        Parcours parcours = new Parcours();
        parcours.setId("123");
        parcours.setDescription("Description originale");

        // Configuration du mock pour retourner le parcours lorsqu'il est recherché par son ID
        when(parcoursRepository.findById("123")).thenReturn(Optional.of(parcours));

        // Création d'un nouveau parcours avec la même ID mais une description différente
        Parcours updatedParcours = new Parcours();
        updatedParcours.setId("123");
        updatedParcours.setDescription("Nouvelle description");

        // Appel de la méthode à tester
        parcoursService.updateParcours(updatedParcours);

        // Capturer l'argument passé à la méthode save
        ArgumentCaptor<Parcours> argumentCaptor = ArgumentCaptor.forClass(Parcours.class);
        verify(parcoursRepository, times(1)).save(argumentCaptor.capture());

        // Vérification que l'argument capturé a les propriétés attendues
        Parcours savedParcours = argumentCaptor.getValue();
        assertEquals("123", savedParcours.getId());
        assertEquals("Nouvelle description", savedParcours.getDescription());
    }

    @Test
    public void testUpdateParcoursWhenParcoursExists() {
        // Configurer le mock pour retourner un parcours existant
        Parcours existingParcours = new Parcours();
        existingParcours.setId("123");
        existingParcours.setDescription("Description originale");
        when(parcoursRepository.findById("123")).thenReturn(Optional.of(existingParcours));

        // Créer un nouveau parcours avec la même ID mais une description différente
        Parcours updatedParcours = new Parcours();
        updatedParcours.setId("123");
        updatedParcours.setDescription("Nouvelle description");

        // Appeler la méthode updateParcours
        parcoursService.updateParcours(updatedParcours);

        // Vérifier que la description du parcours existant a été mise à jour
        assertEquals("Nouvelle description", existingParcours.getDescription());
    }

    @Test
    public void testGetParcoursByIdWhenParcoursDoesNotExist() {
        // Configurer le mock pour retourner un Optional vide
        when(parcoursRepository.findById("123")).thenReturn(Optional.empty());

        // Appeler la méthode getParcoursById et vérifier qu'une exception est lancée
        assertThrows(RuntimeException.class, () -> parcoursService.getParcoursById("123"));
    }

    @Test
    public void testCopyConstructor() {
        // Créer une nouvelle instance de ParcoursService
        ParcoursService original = new ParcoursService();
        original.setParcoursRepository(parcoursRepository);

        // Créer une nouvelle instance de ParcoursService à partir de l'instance originale
        ParcoursService copy = new ParcoursService(original);

        // Vérifier que les deux instances ont le même parcoursRepository
        assertEquals(original.getParcoursRepository(), copy.getParcoursRepository());
    }

    @Test
    public void testUpdateParcoursWhenParcoursDoesNotExist() {
        // Configurer le mock pour retourner un Optional vide
        when(parcoursRepository.findById("123")).thenReturn(Optional.empty());

        // Créer un nouveau parcours avec un ID qui n'existe pas
        Parcours updatedParcours = new Parcours();
        updatedParcours.setId("123");
        updatedParcours.setDescription("Nouvelle description");

        // Appeler la méthode updateParcours et vérifier qu'une exception est lancée
        assertThrows(RuntimeException.class, () -> parcoursService.updateParcours(updatedParcours));
    }


}