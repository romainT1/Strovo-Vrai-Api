package fr.gr3.strovo.api.model;

import java.util.Date;

/**
 * Décrit le filtre correspondant à une recherche.
 */
public class Filter {

    /** Nom du parcours. */
    private String nameParcours;

    /** Date de début du parcours. */
    private String startDate;

    /** Date de fin du parcours. */
    private String endDate;

    /**
     * Crée une instance de Filtre.
     *
     * @param nameParcours nom du parcours
     * @param startDate date de début du parcours
     * @param endDate date de fin du parcours
     */
    public Filter(String nameParcours, String startDate, String endDate) {
        this.nameParcours = nameParcours;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Obtient le nom pour la recherche d'un parcours.
     *
     * @return le nom pour la recherche d'un parcours
     */
    public String getNameParcours() {
        return nameParcours;
    }

    /**
     * Définit le nom pour la recherche d'un parcours.
     *
     * @param nameParcours le nom d'un parcours.
     */
    public void setNameParcours(String nameParcours) {
        this.nameParcours = nameParcours;
    }

    /**
     * Obtient la date de début de recherche du parcours.
     *
     * @return la date de début de la recherche.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Définit la date de début pour la recherche d'un parcours.
     *
     * @param startDate la date d'un parcours.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Obtient la date de fin de recherche du parcours.
     *
     * @return la date de fin de la recherche
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Définit la date de fin pour la recherche d'un parcours.
     *
     * @param endDate la date d'un parcours.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
