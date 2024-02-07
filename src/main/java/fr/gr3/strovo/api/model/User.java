package fr.gr3.strovo.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entité décrivant un utilisateur.
 */
@Entity
@Table(name = "user")
public class User {

    /** Longueur du champ address mail. */
    private static final int EMAIL_LENGTH = 32;

    /** Longueur du champ de mot de passe. */
    private static final int PASSWORD_LENGTH = 32;

    /** Longueur du champ nom de famille. */
    private static final int LASTNAME_LENGTH = 15;

    /** Longueur du champ prénom. */
    private static final int FIRSTNAME_LENGTH = 15;

    /**
     * Identifiant de l'utilisateur.
     * L'identifiant est généré par la méthode auto-increment
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Adresse mail de l'utilisateur.
     * L'adresse mail doit respecter les contraintes suivantes :
     * <ul>
     *     <li>Avoir taille maximale de 32 caractères</li>
     *     <li>Ne pas être null</li>
     *     <li>Etre unique</li>
     * </ul>
     */
    @Column(name = "email", length = EMAIL_LENGTH, nullable = false,
            unique = true)
    private String email;

    /**
     * Mot de passe de l'utilisateur.
     * Le mot de passe doit respecter les contraintes suivantes :
     * <ul>
     *     <li>Avoir taille maximale de 64 caractères</li>
     *     <li>Ne pas être null</li>
     * </ul>
     */
    @Column(name = "password", length = PASSWORD_LENGTH, nullable = false)
    private String password;

    /**
     * Nom de famille de l'utilisateur.
     * Le nom de famille doit respecter les contraintes suivantes :
     * <ul>
     *     <li>Avoir taille maximale de 15 caractères</li>
     *     <li>Ne pas être null</li>
     * </ul>
     */
    @Column(name = "lastname", length = LASTNAME_LENGTH, nullable = false)
    private String lastname;

    /**
     * Prénom de l'utilisateur.
     * Le prénom doit respecter les contraintes suivantes :
     * <ul>
     *     <li>Avoir taille maximale de 15 caractères</li>
     *     <li>Ne pas être null</li>
     * </ul>
     */
    @Column(name = "firstname", length = FIRSTNAME_LENGTH, nullable = false)
    private String firstname;

    /**
     * Constructeur d'instance User.
     */
    public User() { }

    /**
     * Constructeur d'instance User.
     * @param userId identifiant de l'utilisateur
     * @param userEmail adresse mail de l'utilisateur
     * @param userPassword mot de passe de l'utilisateur
     * @param userLastname nom de famille de l'utilisateur
     * @param userFirstname prénom de l'utilisateur
     */
    public User(final int userId, final String userEmail,
                final String userPassword,
                final String userLastname, final String userFirstname) {
        this.id = userId;
        this.email = userEmail;
        this.password = userPassword;
        this.lastname = userLastname;
        this.firstname = userFirstname;
    }

    /**
     * @return l'identifiant de l'utilisateur
     */
    public int getId() {
        return id;
    }

    /**
     * Modifie l'identifiant de l'utilisateur.
     * @param newId nouvelle adresse mail
     */
    public void setId(int newId) {
        this.id = newId;
    }

    /**
     * @return l'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie l'adresse mail de l'utilisateur.
     * @param newEmail nouvelle adresse mail
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * @return le mot de passe de l'utilisateur
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie le mot de passe de l'utilisateur.
     * @param newPassword nouveau mot de passe
     */
    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * @return le nom de famille de l'utilisateur
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Modifie le nom de famille de l'utilisateur.
     * @param newLastname nouveau nom de famille
     */
    public void setLastname(final String newLastname) {
        this.lastname = newLastname;
    }

    /**
     * @return le prénom de l'utilisateur
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Modifie le prénom de l'utilisateur.
     * @param newFirstname nouveau prénom
     */
    public void setFirstname(final String newFirstname) {
        this.firstname = newFirstname;
    }
}
