package fr.gr3.strovo.api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    private Filter filter;

    @BeforeEach
    public void setUp() {
        filter = new Filter("Nom du parcours", "2024-01-01", "2024-01-10");
    }

    @Test
    public void getNameParcours() {
        assertEquals("Nom du parcours", filter.getNameParcours());
    }

    @Test
    public void setNameParcours() {
        filter.setNameParcours("Nouveau nom du parcours");
        assertEquals("Nouveau nom du parcours", filter.getNameParcours());
    }

    @Test
    public void getStartDate() {
        assertEquals("2024-01-01", filter.getStartDate());
    }

    @Test
    public void setStartDate() {
        filter.setStartDate("2024-02-01");
        assertEquals("2024-02-01", filter.getStartDate());
    }

    @Test
    public void getEndDate() {
        assertEquals("2024-01-10", filter.getEndDate());
    }

    @Test
    public void setEndDate() {
        filter.setEndDate("2024-02-10");
        assertEquals("2024-02-10", filter.getEndDate());
    }
}
