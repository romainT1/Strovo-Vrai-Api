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
public class UserModel {
    
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
    @Column(name = "email", length = 32, nullable = false, unique = true)
    private String email; 

    /** 
     * Mot de passe de l'utilisateur.
     * Le mot de passe doit respecter les contraintes suivantes :
     * <ul>
     *     <li>Avoir taille maximale de 64 caractères</li>
     *     <li>Ne pas être null</li>
     * </ul>
     */
    @Column(name = "password", length = 64, nullable = false)
    private String password;

    /** 
     * Nom de famille de l'utilisateur.
     * Le nom de famille doit respecter les contraintes suivantes :
     * <ul>
     *     <li>Avoir taille maximale de 15 caractères</li>
     *     <li>Ne pas être null</li>
     * </ul>
     */
    @Column(name = "lastname", length = 15, nullable = false)
    private String lastname;

    /** 
     * Prénom de l'utilisateur.
     * Le prénom doit respecter les contraintes suivantes :
     * <ul>
     *     <li>Avoir taille maximale de 15 caractères</li>
     *     <li>Ne pas être null</li>
     * </ul>
     */
    @Column(name = "firstname", length = 15, nullable = false)
    private String firstname;

    /**
     * Constructeur d'instance User.
     */
    public UserModel() { }


    /**
     * Constructeur d'instance User.
     * 
     * @param id identifiant de l'utilisateur
     * @param email adresse mail de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @param lastname nom de famille de l'utilisateur
     * @param firstname prénom de l'utilisateur
     */
    public UserModel(int id, String email, String password, String lastname, String firstname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    /**
     * @return l'identifiant de l'utilisateur
     */
    public int getId() {
        return id;
    }

    /**
     * @return l'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie l'adresse mail de l'utilisateur.
     * 
     * @param email nouvelle adresse mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return le mot de passe de l'utilisateur
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie le mot de passe de l'utilisateur.
     * 
     * @param password nouveau mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return le nom de famille de l'utilisateur
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Modifie le nom de famille de l'utilisateur.
     * 
     * @param lastname nouveau nom de famille
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return le prénom de l'utilisateur
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Modifie le prénom de l'utilisateur.
     * 
     * @param firstname nouveau prénom
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}