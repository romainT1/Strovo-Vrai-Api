package fr.gr3.strovo.api.model;

import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParcoursTest {

    @Test
    public void testConstructorAndGetters() {
        // GIVEN différentes valeurs d'un parcours
        String id = "p1";
        int userId = 1;
        String name = "Ptest";
        String description = "Parcours test";
        Date date = new Date();
        long time = 10;
        float speed = 20.0f;
        double distance = 30.0;
        double elevation = 100.5;
        InterestPoint[] interestPoints = { 
            new InterestPoint("IP", "IPTest", new double[]{45.0, 48.2}),
            new InterestPoint("IP", "IPTest", new double[]{60.0, 71.3}),
        };
        double[][] points = {{15.0,14.0},{13.0,18.0}};

        // WHEN on crée une instance de la classe Parcours avec ces valeurs
        Parcours parcours = new Parcours(id, userId, name, description, date, time, speed, distance, elevation, interestPoints, points);

        // THEN les valeurs sont correctement définies
        assertEquals(id, parcours.getId());
        assertEquals(userId, parcours.getUserId());
        assertEquals(name, parcours.getName());
        assertEquals(description, parcours.getDescription());
        assertEquals(date, parcours.getDate());
        assertEquals(time, parcours.getTime());
        assertEquals(speed, parcours.getSpeed());
        assertEquals(distance, parcours.getDistance());
        assertEquals(elevation, parcours.getElevation());
        assertArrayEquals(interestPoints, parcours.getInterestPoints());
        assertArrayEquals(points, parcours.getCoordinates());
    }

    @Test
    public void testSetDate() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();
        
        // WHEN on définit une nouvelle la date
        Date date = new Date();
        parcours.setDate(date);

        // THEN la nouvelle date est correctement définie
        assertEquals(date, parcours.getDate());
    }

    @Test
    public void testSetElevation() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();
        
        // WHEN on définit une nouveau dénivelé
        float elevation = 100.5f;
        parcours.setElevation(elevation);

        // THEN le nouveau dénivelé est correctement défini
        assertEquals(elevation, parcours.getElevation());
    }

    @Test
    public void testSetUserId() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();
        
        // WHEN on définit un nouvel identifiant
        int userId = 456;
        parcours.setUserId(userId);

        // THEN le nouvel identifiant est correctement défini
        assertEquals(userId, parcours.getUserId());
    }

    @Test
    public void testGetInterestPoints() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Vérification que getInterestPointsIds() retourne un tableau vide si interestPointsIds est null
        InterestPoint[] interestPointsIds = parcours.getInterestPoints();
        assertNotNull(interestPointsIds);
        assertEquals(0, interestPointsIds.length);

        // Initialisation de la valeur
        InterestPoint[] interestPoints = { 
            new InterestPoint("IP", "IPTest", new double[]{45.0, 48.2}),
            new InterestPoint("IP", "IPTest", new double[]{60.0, 71.3})
        };

        // Utilisation du setter pour définir la valeur
        parcours.setInterestPoints(interestPoints);

        // Utilisation du getter pour obtenir la valeur
        InterestPoint[] returnedInterestPointsIds = parcours.getInterestPoints();

        // Vérification que interestPointsIds est correctement défini
        assertArrayEquals(interestPoints, returnedInterestPointsIds);

        // Vérification qu'un nouveau tableau a été créé
        assertNotSame(interestPoints, returnedInterestPointsIds);
    }

    @Test
    public void testSetName() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();
        
        // WHEN on définit un nouveau nom
        String name = "ParcoursTest";
        parcours.setName(name);

        // THEN le nouveau nom est correctement défini
        assertEquals(name, parcours.getName());
    }

    @Test
    public void testSetTime() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();

        // WHEN on définit un nouveau temps
        long time = 10;
        parcours.setTime(time);

        // THEN le nouveau temps est correctement défini
        assertEquals(time, parcours.getTime());
    }

    @Test
    public void testSetSpeed() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();

        // WHEN on définit une nouvelle vitesse
        float speed = 20.0f;
        parcours.setSpeed(speed);

        // THEN la nouvelle vitesse est correctement définie
        assertEquals(speed, parcours.getSpeed());
    }

    @Test
    public void testSetDistance() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();

        // WHEN on définit une nouvelle distance
        double distance = 30.0;
        parcours.setDistance(distance);

        // THEN la nouvelle distance est correctement définie
        assertEquals(distance, parcours.getDistance());
    }

    @Test
    public void testSetCoordinates() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();

        /// WHEN on définit de nouvelles coordonnées
        double[][] points = {{15.0,14.0},{13.0,18.0}};
        parcours.setPointsCoordinates(points);

        // THEN les nouvelles coordonnées sont correctement définies
        assertArrayEquals(points, parcours.getCoordinates());
    }
}
