package fr.gr3.strovo.api.model;

import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParcoursTest {

    @Test
    public void testConstructorAndGetters() {
        // Initialisation des valeurs
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

        // Création d'une instance de la classe Parcours avec le constructeur
        Parcours parcours = new Parcours(id, userId, name, description, date, time, averageSpeed, distance, elevation, interestPointsIds, points);

        // Utilisation des getters pour obtenir les valeurs
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
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        Date date = new Date();

        // Utilisation du setter pour définir la valeur
        parcours.setDate(date);

        // Utilisation du getter pour obtenir la valeur
        Date returnedDate = parcours.getDate();

        // Vérification que la date est correctement définie
        assertEquals(date, returnedDate);

        // Vérification qu'une nouvelle instance de Date a été créée
        assertNotSame(date, returnedDate);
    }

    @Test
    public void testSetElevation() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        int[] elevation = { 100, -50 };

        // Utilisation du setter pour définir la valeur
        parcours.setElevation(elevation);

        // Utilisation du getter pour obtenir la valeur
        int[] returnedElevation = parcours.getElevation();

        // Vérification que l'élévation est correctement définie
        assertArrayEquals(elevation, returnedElevation);

        // Vérification qu'un nouveau tableau a été créé
        assertNotSame(elevation, returnedElevation);
    }

    @Test
    public void testSetUserId() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        int userId = 456;

        // Utilisation du setter pour définir la valeur
        parcours.setUserId(userId);

        // Utilisation du getter pour obtenir la valeur
        int returnedUserId = parcours.getUserId();

        // Vérification que l'userId est correctement défini
        assertEquals(userId, returnedUserId);
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
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        String name = "ParcoursTest";

        // Utilisation du setter pour définir la valeur
        parcours.setName(name);

        // Utilisation du getter pour obtenir la valeur
        String returnedName = parcours.getName();

        // Vérification que le nom est correctement défini
        assertEquals(name, returnedName);
    }

    @Test
    public void testSetTime() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        double time = 10.5;

        // Utilisation du setter pour définir la valeur
        parcours.setTime(time);

        // Utilisation du getter pour obtenir la valeur
        double returnedTime = parcours.getTime();

        // Vérification que le temps est correctement défini
        assertEquals(time, returnedTime, 0.001);
    }

    @Test
    public void testSetAverageSpeed() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        float averageSpeed = 20.0f;

        // Utilisation du setter pour définir la valeur
        parcours.setAverageSpeed(averageSpeed);

        // Utilisation du getter pour obtenir la valeur
        float returnedAverageSpeed = parcours.getAverageSpeed();

        // Vérification que la vitesse moyenne est correctement définie
        assertEquals(averageSpeed, returnedAverageSpeed, 0.001f);
    }

    @Test
    public void testSetDistance() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        float distance = 30.0f;

        // Utilisation du setter pour définir la valeur
        parcours.setDistance(distance);

        // Utilisation du getter pour obtenir la valeur
        float returnedDistance = parcours.getDistance();

        // Vérification que la distance est correctement définie
        assertEquals(distance, returnedDistance, 0.001f);
    }

    @Test
    public void testSetPoints() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

        // Initialisation de la valeur
        double[][] points = {{15.0,14.0},{13.0,18.0}};

        // Utilisation du setter pour définir la valeur
        parcours.setPointsCoordinates(points);

        // Utilisation du getter pour obtenir la valeur
        double[][] returnedPoints = parcours.getCoordinates();

        // Vérification que les points sont correctement définis
        assertArrayEquals(points, returnedPoints);
    }
}
