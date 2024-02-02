package fr.gr3.strovo.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//TODO JAVADOC
@Document("interestPoint")
public class InterestPoint {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("coordinates")
    private int[] coordinates;

    public InterestPoint(String id, String name, String description, int[] coordinates) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
    }

    /**
     * Constructeur par défaut requis pour Mockito.
     */
    public InterestPoint() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }
}

