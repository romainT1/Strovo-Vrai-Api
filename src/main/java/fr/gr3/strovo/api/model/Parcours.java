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
    private long time;

    /** Vitesse moyenne du parcours. */
    @Field
    @JsonProperty("speed")
    private float speed;

    /** Distance parcourue. */
    @Field
    @JsonProperty("distance")
    private double distance;

    /** Dénivelé du parcours. */
    @Field
    @JsonProperty("elevation")
    private double elevation;

    /** Liste des points d'intérêts associés au parcours. */
    @Field
    @JsonProperty("interestPoints")
    private InterestPoint[] interestPoints;

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
     * @param speedParcours vitesse moyenne de l'utilisateur
     * @param distanceParcours distance parcourue par l'utilisateur
     * @param elevationParcours dénivelé positif et négatif du parcours
     * @param interestPointsParcours liste des points d'intérêts associés
     *                                  au parcours
     * @param coordinatesParcours liste des coordonnées de points
     *                            formant le parcours
     */
    public Parcours(final String idParcours,
                    final int userIdParcours,
                    final String nameParcours,
                    final String descriptionParcours,
                    final Date dateParcours,
                    final long timeParcours,
                    final float speedParcours,
                    final double distanceParcours,
                    final double elevationParcours,
                    final InterestPoint[] interestPointsParcours,
                    final double[][] coordinatesParcours) {
        this.id = idParcours;
        this.userId = userIdParcours;
        this.name = nameParcours;
        this.description = descriptionParcours;
        this.date = new Date(dateParcours.getTime());
        this.time = timeParcours;
        this.speed = speedParcours;
        this.distance = distanceParcours;
        this.elevation = elevationParcours;
        this.interestPoints = interestPointsParcours.clone();
        this.coordinates = coordinatesParcours.clone();
    }

    /**
     * Constructeur par défaut requis pour Mockito.
     */
    public Parcours() {
        interestPoints = new InterestPoint[]{};
        coordinates = new double[][]{};
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
        return new Date(date.getTime());
    }

    /**
     * Définit la date du parcours.
     *
     * @param dateParcours Description du parcours.
     */
    public void setDate(final Date dateParcours) {
        this.date = new Date(dateParcours.getTime());
    }

    /**
     * Obtient le temps du parcours.
     *
     * @return Temps du parcours.
     */
    public long getTime() {
        return time;
    }

    /**
     * Définit le temps du parcours.
     *
     * @param timeParcours Temps du parcours.
     */
    public void setTime(final long timeParcours) {
        this.time = timeParcours;
    }

    /**
     * Obtient la vitesse moyenne du parcours.
     *
     * @return Vitesse moyenne du parcours.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Définit la vitesse moyenne du parcours.
     *
     * @param speedParcours Vitesse moyenne du parcours.
     */
    public void setSpeed(final float speedParcours) {
        this.speed = speedParcours;
    }

    /**
     * Obtient la distance du parcours.
     *
     * @return Distance du parcours.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Définit la distance du parcours.
     *
     * @param distanceParcours Distance du parcours.
     */
    public void setDistance(final double distanceParcours) {
        this.distance = distanceParcours;
    }

    /**
     * Obtient le dénivelé positif et négatif du parcours.
     *
     * @return Dénivelé du parcours.
     */
    public double getElevation() {
        return elevation;
    }

    /**
     * Définit le dénivelé positif et négatif du parcours.
     *
     * @param elevationParcours Dénivelé positif et négatif du parcours.
     */
    public void setElevation(final double elevationParcours) {
        this.elevation = elevationParcours;
    }

    /**
     * Obtient les identifiants des points d'interêts rattachés au parcours.
     *
     * @return Identifiants des points d'interêts du parcours.
     */
    public InterestPoint[] getInterestPoints() {
        return interestPoints.clone();
    }

    /**
     * Définit les identifiants des points d'interêts du parcours.
     *
     * @param interestPointsParcours Identifiants des points
     *                                  d'interêts du parcours.
     */
    public void setInterestPoints(final InterestPoint[]
                                        interestPointsParcours) {
        this.interestPoints = interestPointsParcours.clone();
    }

    /**
     * Obtient les coordonnées des points du parcours.
     *
     * @return Points du parcours.
     */
    public double[][] getCoordinates() {
        return coordinates.clone();
    }

    /**
     * Définit les coordonnées des points du parcours.
     *
     * @param newCoordinates Points du parcours.
     */
    public void setPointsCoordinates(final double[][] newCoordinates) {
        this.coordinates = newCoordinates.clone();
    }
}
