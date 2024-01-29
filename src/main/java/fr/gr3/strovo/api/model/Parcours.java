package fr.gr3.strovo.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

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

    /** Liste des points formant le parcours. */
    @Field
    @JsonProperty("points")
    private String points;

    /**
     * Crée une instance de Parcours.
     *
     * @param id identifiant unique du parcours
     * @param userId identifiant unique de l'utilisateur ayant enregistré le parcours
     * @param name nom donné au parcours
     * @param description description du parcours
     * @param date date d'enregistrement du parcours
     * @param time durée du parcours
     * @param averageSpeed vitesse moyenne de l'utilisateur
     * @param distance distance parcourue par l'utilisateur
     * @param elevation dénivelé positif et négatif du parcours
     * @param interestPointsIds liste des points d'intérêts associés au parcours
     * @param points liste des points formant le parcours
     */
    public Parcours(String id, int userId, String name, String description, Date date, double time, float averageSpeed, float distance, int[] elevation, String[] interestPointsIds, String points) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.averageSpeed = averageSpeed;
        this.distance = distance;
        this.elevation = elevation;
        this.interestPointsIds = interestPointsIds;
        this.points = points;
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
     * @param id Identifiant du parcours.
     */
    public void setId(String id) {
        this.id = id;
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
     * @param userId Identifiant de l'utilisateur.
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * @param name Nom du parcours.
     */
    public void setName(String name) {
        this.name = name;
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
     * @param description Description du parcours.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @param date Description du parcours.
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @param time Temps du parcours.
     */
    public void setTime(double time) {
        this.time = time;
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
     * @param averageSpeed Vitesse moyenne du parcours.
     */
    public void setAverageSpeed(float averageSpeed) {
        this.averageSpeed = averageSpeed;
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
     * @param distance Distance du parcours.
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * Obtient le dénivelé positif et négatif du parcours.
     *
     * @return Dénivelé du parcours.
     */
    public int[] getElevation() {
        return elevation;
    }

    /**
     * Définit le dénivelé positif et négatif du parcours.
     *
     * @param elevation Dénivelé positif et négatif du parcours.
     */
    public void setElevation(int[] elevation) {
        this.elevation = elevation;
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
     * @param interestPointsIds Identifiants des points d'interêts du parcours.
     */
    public void setInterestPointsIds(String[] interestPointsIds) {
        this.interestPointsIds = interestPointsIds;
    }

    /**
     * Obtient les points du parcours.
     *
     * @return Points du parcours.
     */
    public String getPoints() {
        return points;
    }

    /**
     * Définit les points du parcours.
     *
     * @param points Points du parcours.
     */
    public void setPoints(String points) {
        this.points = points;
    }
}
