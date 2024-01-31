package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.repository.ParcoursRepository;
import org.springframework.stereotype.Service;

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
    public ParcoursService(final ParcoursRepository parcoursRepository) {
        this.parcoursRepository = parcoursRepository;
    }

    /**
     * Construit un nouveau ParcoursService en copiant les champs
     * de l'instance originale de ParcoursService fournie.
     *
     * @param original L'instance originale de ParcoursService à copier.
     */
    public ParcoursService(ParcoursService original) {
        this.parcoursRepository = original.parcoursRepository;
    }

    /**
     * Ajoute un nouveau parcours.
     *
     * @param parcours Parcours à ajouter.
     */
    public void addParcours(final Parcours parcours) {
        parcoursRepository.insert(parcours);
    }

}
