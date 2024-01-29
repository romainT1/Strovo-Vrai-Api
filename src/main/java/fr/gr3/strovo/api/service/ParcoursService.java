package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.repository.ParcoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des parcours.
 */
@Service
public class ParcoursService {

    /**
     * Repository pour l'accès aux données des parcours.
     */
    private final ParcoursRepository parcoursRepository;

    /**
     * Constructeur du service.
     *
     * @param parcoursRepository Repository des parcours à injecter.
     */
    public ParcoursService(ParcoursRepository parcoursRepository) {
        this.parcoursRepository = parcoursRepository;
    }

    /**
     * Ajoute un nouveau parcours.
     *
     * @param parcours Parcours à ajouter.
     */
    public void addParcours(Parcours parcours) {
        parcoursRepository.insert(parcours);
    }
}