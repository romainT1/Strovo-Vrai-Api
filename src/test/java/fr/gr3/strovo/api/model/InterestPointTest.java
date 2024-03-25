package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InterestPointTest {

    private InterestPoint interestPoint;
    private double[] coordinates = {10.2, 20.5};

    @BeforeEach
    public void setUp() {
        // GIVEN un point d'intérêt est initialisé avant chaque test
        interestPoint = new InterestPoint("Cathédrale de Rodez", "Une cathédrale iconique de Rodez", coordinates);
    }

    @Test
    public void testGetName() {
        // THEN le nom correspond à celui défini à l'initialisation
        assertEquals("Cathédrale de Rodez", interestPoint.getName());
    }

    @Test
    public void testSetName() {
        // WHEN le nom est modifié
        interestPoint.setName("Musée du Louvre");
        // THEN le nouveau nom est correctement mis à jour
        assertEquals("Musée du Louvre", interestPoint.getName());
    }

    @Test
    public void testGetDescription() {
        // THEN la description correspond à celle définie à l'initialisation
        assertEquals("Une cathédrale iconique de Rodez", interestPoint.getDescription());
    }

    @Test
    public void testSetDescription() {
        interestPoint.setDescription("Le plus grand musée d'art");
        // THEN la nouvelle description est correctement mise à jour
        assertEquals("Le plus grand musée d'art", interestPoint.getDescription());
    }

    @Test
    public void testGetCoordinates() {
        // THEN les coordonnées correspondent à celles définies à l'initialisation
        assertArrayEquals(coordinates, interestPoint.getCoordinates());
    }

    @Test
    public void testSetCoordinates() {
        // WHEN les coordonnées sont modifiées
        double[] newCoordinates = {30.5, 40.3};
        interestPoint.setCoordinates(newCoordinates);
        // THEN les nouvelles coordonnées sont correctement mises à jour
        assertArrayEquals(newCoordinates, interestPoint.getCoordinates());
    }

    @Test
    public void testDefaultConstructor() {
        // GIVEN un point d'intérêt est créé avec le constructeur par défaut
        InterestPoint defaultInterestPoint = new InterestPoint();
        // WHEN les propriétés sont définies
        defaultInterestPoint.setName("Cathédrale Notre Dame");
        defaultInterestPoint.setDescription("Une cathédrale médiévale");
        defaultInterestPoint.setCoordinates(new double[]{50.5, 60.5});
        // THEN les propriétés sont correctement définies
        assertEquals("Cathédrale Notre Dame", defaultInterestPoint.getName());
        assertEquals("Une cathédrale médiévale", defaultInterestPoint.getDescription());
        assertArrayEquals(new double[]{50.5, 60.5}, defaultInterestPoint.getCoordinates());
    }
}

