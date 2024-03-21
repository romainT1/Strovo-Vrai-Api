package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInterestPoint {

    private InterestPoint interestPoint;
    private double[] coordinates = {10.2, 20.5};

    @BeforeEach
    public void setUp() {
        interestPoint = new InterestPoint("Cathédrale de Rodez", "Une cathédrale iconique de Rodez", coordinates);
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
        double[] newCoordinates = {30.5, 40.3};
        interestPoint.setCoordinates(newCoordinates);
        assertArrayEquals(newCoordinates, interestPoint.getCoordinates());
    }

    @Test
    public void testDefaultConstructor() {
        InterestPoint defaultInterestPoint = new InterestPoint();
        defaultInterestPoint.setName("Cathédrale Notre Dame");
        defaultInterestPoint.setDescription("Une cathédrale médiévale");
        defaultInterestPoint.setCoordinates(new double[]{50.5, 60.5});
        assertEquals("Cathédrale Notre Dame", defaultInterestPoint.getName());
        assertEquals("Une cathédrale médiévale", defaultInterestPoint.getDescription());
        assertArrayEquals(new double[]{50.5, 60.5}, defaultInterestPoint.getCoordinates());
    }
}
