package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestInterestPoint {

    private InterestPoint interestPoint;
    private int[] coordinates = {10, 20};

    @BeforeEach
    public void setUp() {
        interestPoint = new InterestPoint("1", "Cathédrale de Rodez", "Une cathédrale iconique de Rodez", coordinates);
    }

    @Test
    public void testGetId() {
        assertEquals("1", interestPoint.getId());
    }

    @Test
    public void testSetId() {
        interestPoint.setId("2");
        assertEquals("2", interestPoint.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Cathédrale de Rodez", interestPoint.getName());
    }

    @Test
    public void testSetName() {
        interestPoint.setName("Musée du Louvre");
        assertEquals("Musée du Louvre", interestPoint.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Une cathédrale iconique de Rodez", interestPoint.getDescription());
    }

    @Test
    public void testSetDescription() {
        interestPoint.setDescription("Le plus grand musée d'art");
        assertEquals("Le plus grand musée d'art", interestPoint.getDescription());
    }

    @Test
    public void testGetCoordinates() {
        assertArrayEquals(coordinates, interestPoint.getCoordinates());
    }

    @Test
    public void testSetCoordinates() {
        int[] newCoordinates = {30, 40};
        interestPoint.setCoordinates(newCoordinates);
        assertArrayEquals(newCoordinates, interestPoint.getCoordinates());
    }

    @Test
    public void testDefaultConstructor() {
        InterestPoint defaultInterestPoint = new InterestPoint();
        defaultInterestPoint.setId("3");
        defaultInterestPoint.setName("Cathédrale Notre Dame");
        defaultInterestPoint.setDescription("Une cathédrale médiévale");
        defaultInterestPoint.setCoordinates(new int[]{50, 60});
        assertEquals("3", defaultInterestPoint.getId());
        assertEquals("Cathédrale Notre Dame", defaultInterestPoint.getName());
        assertEquals("Une cathédrale médiévale", defaultInterestPoint.getDescription());
        assertArrayEquals(new int[]{50, 60}, defaultInterestPoint.getCoordinates());
    }
}