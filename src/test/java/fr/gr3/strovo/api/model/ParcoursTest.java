package fr.gr3.strovo.api.model;

import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParcoursTest {

    @Test
    public void testConstructorAndGetters() {
        // GIVEN différentes valeurs d'un parcours
        String id = "123";
        int userId = 456;
        String name = "ParcoursTest";
        String description = "Description du parcours de test";
        Date date = new Date();
        double time = 10.5;
        float averageSpeed = 20.0f;
        float distance = 30.0f;
        int[] elevation = { 100, -50 };
        String[] interestPointsIds = { "poi1", "poi2" };
        double[][] points = {{15.0,14.0},{13.0,18.0}};

        // WHEN on crée une instance de la classe Parcours avec ces valeurs
        Parcours parcours = new Parcours(id, userId, name, description, date, time, averageSpeed, distance, elevation, interestPointsIds, points);

        // THEN les valeurs sont correctement définies
        assertEquals(id, parcours.getId());
        assertEquals(userId, parcours.getUserId());
        assertEquals(name, parcours.getName());
        assertEquals(description, parcours.getDescription());
        assertEquals(date, parcours.getDate());
        assertEquals(time, parcours.getTime(), 0.001);
        assertEquals(averageSpeed, parcours.getAverageSpeed(), 0.001);
        assertEquals(distance, parcours.getDistance(), 0.001);
        assertArrayEquals(elevation, parcours.getElevation());
        assertArrayEquals(interestPointsIds, parcours.getInterestPointsIds());
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
        int[] elevation = { 100, -50 };
        parcours.setElevation(elevation);

        // THEN le nouveau dénivelé est correctement défini
        assertArrayEquals(elevation, parcours.getElevation());
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

// Vous pouvez tester les autres setters de manière similaire à testSetUserId()

    @Test
    public void testGetInterestPointsIds() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Vérification que getInterestPointsIds() retourne un tableau vide si interestPointsIds est null
        String[] interestPointsIds = parcours.getInterestPointsIds();
        assertNotNull(interestPointsIds);
        assertEquals(0, interestPointsIds.length);

        // Initialisation de la valeur
        String[] newInterestPointsIds = { "poi1", "poi2" };

        // Utilisation du setter pour définir la valeur
        parcours.setInterestPointsIds(newInterestPointsIds);

        // Utilisation du getter pour obtenir la valeur
        String[] returnedInterestPointsIds = parcours.getInterestPointsIds();

        // Vérification que interestPointsIds est correctement défini
        assertArrayEquals(newInterestPointsIds, returnedInterestPointsIds);

        // Vérification qu'un nouveau tableau a été créé
        assertNotSame(newInterestPointsIds, returnedInterestPointsIds);
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
        double time = 10.5;
        parcours.setTime(time);

        // THEN le nouveau temps est correctement défini
        assertEquals(time, parcours.getTime());
    }

    @Test
    public void testSetAverageSpeed() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();

        // WHEN on définit une nouvelle vitesse
        float averageSpeed = 20.0f;
        parcours.setAverageSpeed(averageSpeed);

        // THEN la nouvelle vitesse est correctement définie
        assertEquals(averageSpeed, parcours.getAverageSpeed());
    }

    @Test
    public void testSetDistance() {
        // GIVEN une instance vide de la classe Parcours
        Parcours parcours = new Parcours();

        // WHEN on définit une nouvelle distance
        float distance = 30.0f;
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
