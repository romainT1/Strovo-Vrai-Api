package fr.gr3.strovo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Classe de l'application API Strovo.
 */
@SpringBootApplication
public class StrovoApiApplication {

    /**
     * Constructeur privé de StrovoApiApplication.
     */
    public StrovoApiApplication() {

    }

    /**
     * Methode de lancement de l'API Strovo.
     * @param args argument
     */
    public static void main(final String[] args) {
        SpringApplication.run(StrovoApiApplication.class, args);
    }

}
