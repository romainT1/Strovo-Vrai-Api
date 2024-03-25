package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

    private Filter filter;

    @BeforeEach
    public void setUp() {
        // GIVEN un filtre est initialisé avec des valeurs spécifiques avant chaque test
        filter = new Filter("Nom du parcours", "2024-01-01", "2024-01-10");
    }

    @Test
    public void getNameParcours() {
        // THEN le nom du parcours correspond à celui défini à l'initialisation
        assertEquals("Nom du parcours", filter.getNameParcours());
    }

    @Test
    public void setNameParcours() {
        // WHEN le nom du parcours est modifié
        filter.setNameParcours("Nouveau nom du parcours");
        // THEN le nouveau nom du parcours est correctement mis à jour
        assertEquals("Nouveau nom du parcours", filter.getNameParcours());
    }

    @Test
    public void getStartDate() {
        // THEN la date de début correspond à celle définie à l'initialisation
        assertEquals("2024-01-01", filter.getStartDate());
    }

    @Test
    public void setStartDate() {
        // WHEN la date de début est modifiée
        filter.setStartDate("2024-02-01");
        // THEN la nouvelle date de début est correctement mise à jour
        assertEquals("2024-02-01", filter.getStartDate());
    }

    @Test
    public void getEndDate() {
        // THEN la date de fin correspond à celle définie à l'initialisation
        assertEquals("2024-01-10", filter.getEndDate());
    }

    @Test
    public void setEndDate() {
        // WHEN la date de fin est modifiée
        filter.setEndDate("2024-02-10");
        // THEN la nouvelle date de fin est correctement mise à jour
        assertEquals("2024-02-10", filter.getEndDate());
    }
}





