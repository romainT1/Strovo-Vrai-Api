package fr.gr3.strovo.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Document décrivant un Parcours effectué par un utilisateur.
 */
@Document("parcours")
public class Parcours {

    /** Identifiant unique du parcours. */
    @Id
    @JsonProperty("id")
    private String id;

    /** Identifiant unique de l'utilisateur. */
    @Field
    @JsonProperty("userId")
    private int userId;

    /** Nom du parcours. */
    @Field
    @JsonProperty("name")
    private String name;

    /** Description du parcours. */
    @Field
    @JsonProperty("description")
    private String description;

    /** Date du parcours. */
    @Field
    @JsonProperty("date")
    private Date date;

    /** Durée du parcours. */
    @Field
    @JsonProperty("time")
    private double time;

    /** Vitesse moyenne du parcours. */
    @Field
    @JsonProperty("averageSpeed")
    private float averageSpeed;

    /** Distance parcourue. */
    @Field
    @JsonProperty("distance")
    private float distance;

    /** Dénivelé du parcours. */
    @Field
    @JsonProperty("elevation")
    private int[] elevation;

    /** Liste des points d'intérêts associés au parcours. */
    @Field
    @JsonProperty("interestPointsIds")
    private String[] interestPointsIds;

    /** Liste de coordonnées de points sous la forme [[lat, long], ...]. */
    @Field
    @JsonProperty("coordinates")
    private double[][] coordinates;

    /**
     * Crée une instance de Parcours.
     *
     * @param idParcours identifiant unique du parcours
     * @param userIdParcours identifiant unique de l'utilisateur ayant
     *                       enregistré le parcours
     * @param nameParcours nom donné au parcours
     * @param descriptionParcours description du parcours
     * @param dateParcours date d'enregistrement du parcours
     * @param timeParcours durée du parcours
     * @param averageSpeedParcours vitesse moyenne de l'utilisateur
     * @param distanceParcours distance parcourue par l'utilisateur
     * @param elevationParcours dénivelé positif et négatif du parcours
     * @param interestPointsIdsParcours liste des points d'intérêts associés
     *                                  au parcours
     * @param coordinates liste des coordonnées de points formant le parcours
     */
    public Parcours(final String idParcours,
                    final int userIdParcours,
                    final String nameParcours,
                    final String descriptionParcours,
                    final Date dateParcours,
                    final double timeParcours,
                    final float averageSpeedParcours,
                    final float distanceParcours,
                    final int[] elevationParcours,
                    final String[] interestPointsIdsParcours,
                    final double[][] coordinates) {
        this.id = idParcours;
        this.userId = userIdParcours;
        this.name = nameParcours;
        this.description = descriptionParcours;
        this.date = dateParcours;
        this.time = timeParcours;
        this.averageSpeed = averageSpeedParcours;
        this.distance = distanceParcours;
        this.elevation = elevationParcours.clone();
        this.interestPointsIds = interestPointsIdsParcours.clone();
        this.coordinates = coordinates.clone();
    }

    /**
     * Constructeur par défaut requis pour Mockito.
     */
    public Parcours() {
    }

    /**
     * Obtient l'identifiant du parcours.
     *
     * @return Identifiant du parcours.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant du parcours.
     *
     * @param idParcours Identifiant du parcours.
     */
    public void setId(final String idParcours) {
        this.id = idParcours;
    }

    /**
     * Obtient l'identifiant de l'utilisateur associé au parcours.
     *
     * @return Identifiant de l'utilisateur.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Définit l'identifiant de l'utilisateur associé au parcours.
     *
     * @param userIdParcours Identifiant de l'utilisateur.
     */
    public void setUserId(final int userIdParcours) {
        this.userId = userIdParcours;
    }

    /**
     * Obtient le nom du parcours.
     *
     * @return Nom du parcours.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du parcours.
     *
     * @param nameParcours Nom du parcours.
     */
    public void setName(final String nameParcours) {
        this.name = nameParcours;
    }

    /**
     * Obtient la description du parcours.
     *
     * @return Description du parcours.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du parcours.
     *
     * @param descriptionParcours Description du parcours.
     */
    public void setDescription(final String descriptionParcours) {
        this.description = descriptionParcours;
    }

    /**
     * Obtient la date du parcours.
     *
     * @return Date du parcours.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Définit la date du parcours.
     *
     * @param dateParcours Description du parcours.
     */
    public void setDate(final Date dateParcours) {
        this.date = dateParcours;
    }

    /**
     * Obtient le temps du parcours.
     *
     * @return Temps du parcours.
     */
    public double getTime() {
        return time;
    }

    /**
     * Définit le temps du parcours.
     *
     * @param timeParcours Temps du parcours.
     */
    public void setTime(final double timeParcours) {
        this.time = timeParcours;
    }

    /**
     * Obtient la vitesse moyenne du parcours.
     *
     * @return Vitesse moyenne du parcours.
     */
    public float getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Définit la vitesse moyenne du parcours.
     *
     * @param averageSpeedParcours Vitesse moyenne du parcours.
     */
    public void setAverageSpeed(final float averageSpeedParcours) {
        this.averageSpeed = averageSpeedParcours;
    }

    /**
     * Obtient la distance du parcours.
     *
     * @return Distance du parcours.
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Définit la distance du parcours.
     *
     * @param distanceParcours Distance du parcours.
     */
    public void setDistance(final float distanceParcours) {
        this.distance = distanceParcours;
    }

    /**
     * Obtient le dénivelé positif et négatif du parcours.
     *
     * @return Dénivelé du parcours.
     */
    public int[] getElevation() {
        return elevation.clone();
    }

    /**
     * Définit le dénivelé positif et négatif du parcours.
     *
     * @param elevationParcours Dénivelé positif et négatif du parcours.
     */
    public void setElevation(final int[] elevationParcours) {
        this.elevation = elevationParcours.clone();
    }

    /**
     * Obtient les identifiants des points d'interêts rattachés au parcours.
     *
     * @return Identifiants des points d'interêts du parcours.
     */
    public String[] getInterestPointsIds() {
        return interestPointsIds != null ? interestPointsIds : new String[0];
    }

    /**
     * Définit les identifiants des points d'interêts du parcours.
     *
     * @param interestPointsIdsParcours Identifiants des points
     *                                  d'interêts du parcours.
     */
    public void setInterestPointsIds(final String[] interestPointsIdsParcours) {
        this.interestPointsIds = interestPointsIdsParcours.clone();
    }

    /**
     * Obtient les coordonnées des points du parcours.
     *
     * @return Points du parcours.
     */
    public double[][] getCoordinates() {
        return coordinates;
    }

    /**
     * Définit les coordonnées des points du parcours.
     *
     * @param coordinates Points du parcours.
     */
    public void setPointsCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }
}
