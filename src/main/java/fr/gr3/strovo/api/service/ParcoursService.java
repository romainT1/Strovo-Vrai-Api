package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.repository.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service pour la gestion des parcours.
 */
@Service
public class ParcoursService {

    /**
     * Repository pour l'accès aux données des parcours.
     */
    @Autowired
    private ParcoursRepository parcoursRepository;

    /**
     * Constructeur du service.
     */
    public ParcoursService() {
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

    /**
     * Récupère un parcours par son Id.
     *
     * @param parcoursId Identifiant du parcours à rechercher.
     */
    public Parcours getParcoursById(String parcoursId) {
        return parcoursRepository.findById(parcoursId).orElseThrow(()
                -> new RuntimeException(String.format("Cannot Find Parcours by Parcours Id - %s", parcoursId)));
    }
}
