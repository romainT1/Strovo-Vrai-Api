package fr.gr3.strovo.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class ParcoursTest {

    @Test
    public void testGettersAndSetters() {
        // Création d'une instance de la classe Parcours
        Parcours parcours = new Parcours();

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
        String points = "1,2;3,4;5,6";

        // Utilisation des setters pour définir les valeurs
        parcours.setId(id);
        parcours.setUserId(userId);
        parcours.setName(name);
        parcours.setDescription(description);
        parcours.setDate(date);
        parcours.setTime(time);
        parcours.setAverageSpeed(averageSpeed);
        parcours.setDistance(distance);
        parcours.setElevation(elevation);
        parcours.setInterestPointsIds(interestPointsIds);
        parcours.setPoints(points);

        // Utilisation des getters pour obtenir les valeurs
        assertEquals(id, parcours.getId());
        assertEquals(userId, parcours.getUserId());
        assertEquals(name, parcours.getName());
        assertEquals(description, parcours.getDescription());
        assertEquals(date, parcours.getDate());
        assertEquals(time, parcours.getTime());
        assertEquals(averageSpeed, parcours.getAverageSpeed());
        assertEquals(distance, parcours.getDistance());
        assertArrayEquals(elevation, parcours.getElevation());
        assertArrayEquals(interestPointsIds, parcours.getInterestPointsIds());
        assertEquals(points, parcours.getPoints());
    }
}

