package fr.gr3.strovo.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Modèle pour un point d'intérêt.
 */
@Document("interestPoint")
public class InterestPoint {

    /**
     * L'identifiant du point d'intérêt.
     */
    @Id
    @JsonProperty("id")
    private String id;

    /**
     * Le nom du point d'intérêt.
     */
    @JsonProperty("name")
    private String name;

    /**
     * La description du point d'intérêt.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Les coordonnées du point d'intérêt.
     */
    @JsonProperty("coordinates")
    private int[] coordinates;

    /**
     * Constructeur pour un point d'intérêt.
     *
     * @param idParam L'identifiant du point d'intérêt.
     * @param nameParam Le nom du point d'intérêt.
     * @param descriptionParam La description du point d'intérêt.
     * @param coordinatesParam Les coordonnées du point d'intérêt.
     */
    public InterestPoint(final String idParam, final String nameParam,
                         final String descriptionParam,
                         final int[] coordinatesParam) {
        this.id = idParam;
        this.name = nameParam;
        this.description = descriptionParam;
        this.coordinates = coordinatesParam.clone();
    }

    /**
     * Constructeur par défaut requis pour Mockito.
     */
    public InterestPoint() {
    }

    /**
     * Récupère l'identifiant du point d'intérêt.
     * @return L'identifiant du point d'intérêt.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant du point d'intérêt.
     * @param idParam L'identifiant du point d'intérêt.
     */
    public void setId(final String idParam) {
        this.id = idParam;
    }

    /**
     * Récupère le nom du point d'intérêt.
     * @return Le nom du point d'intérêt.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du point d'intérêt.
     * @param nameParam Le nom du point d'intérêt.
     */
    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    /**
     * Récupère la description du point d'intérêt.
     * @return La description du point d'intérêt.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du point d'intérêt.
     * @param descriptionParam La description du point d'intérêt.
     */
    public void setDescription(final String descriptionParam) {
        this.description = descriptionParam;
    }

    /**
     * Récupère les coordonnées du point d'intérêt.
     * @return Les coordonnées du point d'intérêt.
     */
    public int[] getCoordinates() {
        return coordinates.clone();
    }

    /**
     * Définit les coordonnées du point d'intérêt.
     * @param coordinatesParam Les coordonnées du point d'intérêt.
     */
    public void setCoordinates(final int[] coordinatesParam) {
        this.coordinates = coordinatesParam.clone();
    }
}
