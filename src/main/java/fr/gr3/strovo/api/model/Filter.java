package fr.gr3.strovo.api.model;


import java.util.Date;


/**
 * Décrit le filtre correspondant à une recherche.
 */
public class Filter {


    /** Nom du parcours. */
    private String nameParcours;


    /** Date de début du parcours. */
    private Date startDate;


    /** Date de fin du parcours. */
    private Date endDate;


    /**
     * Crée une instance de Filtre.
     *
     * @param finalNameParcours nom du parcours
     * @param finalStartDate date de début du parcours
     * @param finalEndDate date de fin du parcours
     */
    public Filter(final String finalNameParcours,
                  final Date finalStartDate,
                  final Date finalEndDate) {
        this.nameParcours = finalNameParcours;
        this.startDate = finalStartDate;
        this.endDate = finalEndDate;
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
     * @param finalNameParcours le nom d'un parcours.
     */
    public void setNameParcours(final String finalNameParcours) {
        this.nameParcours = finalNameParcours;
    }


    /**
     * Obtient la date de début de recherche du parcours.
     *
     * @return la date de début de la recherche.
     */
    public Date getStartDate() {
        return startDate;
    }


    /**
     * Définit la date de début pour la recherche d'un parcours.
     *
     * @param finalStartDate la date d'un parcours.
     */
    public void setStartDate(final Date finalStartDate) {
        this.startDate = finalStartDate;
    }


    /**
     * Obtient la date de fin de recherche du parcours.
     *
     * @return la date de fin de la recherche
     */
    public Date getEndDate() {
        return endDate;
    }


    /**
     * Définit la date de fin pour la recherche d'un parcours.
     *
     * @param finalEndDate la date d'un parcours.
     */
    public void setEndDate(final Date finalEndDate) {
        this.endDate = finalEndDate;
    }
}

