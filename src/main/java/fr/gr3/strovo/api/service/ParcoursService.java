package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.repository.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ParcoursRepository parcoursRepository;

    /**
     * Constructeur du service.
     */
    public ParcoursService() {
    }

    /**
     * Construit un nouveau ParcoursService en copiant les champs
     * de l'instance originale de ParcoursService fourni.
     *
     * @param original L'instance originale de ParcoursService à copier.
     */
    public ParcoursService(final ParcoursService original) {
        this.parcoursRepository = original.parcoursRepository;
    }

    /**
     * Associe le repository au service.
     *
     * @param parcoursRepositoryImpl le repository.
     */
    public void setParcoursRepository(final ParcoursRepository
                                              parcoursRepositoryImpl) {
        this.parcoursRepository = parcoursRepositoryImpl;
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
     * @return le parcours correspondant à l'identifiant
     */
    public Parcours getParcoursById(final String parcoursId) {
        return parcoursRepository.findById(parcoursId).orElseThrow(()
                -> new RuntimeException(String.format(
                        "Cannot Find Parcours by Parcours Id - %s",
                parcoursId)));
    }

    /**
     * Récupère la liste des parcours d'un utilisateur.
     *
     * @param userId Identifiant de l'utilisateur.
     * @param filter Filtre correspondant à la recherche.
     * @return les parcours associés à l'utilisateur et aux filtres
     */
    public List<Parcours> getParcoursByUserIdAndFilters(
            final int userId, final Filter filter) {

        return parcoursRepository.findAllByUserIdAndFilters(userId, filter);
    }

    /**
     * Supprime un parcours.
     *
     * @param parcoursId ID du parcours à supprimer.
     */
    public void deleteParcours(final String parcoursId) {
        parcoursRepository.deleteById(parcoursId);
    }

    /**
     * Modifie la description d'un parcours.
     *
     * @param parcours parcours a modifier.
     */
    public void updateParcours(final Parcours parcours) {
        Parcours parcoursModify = parcoursRepository.findById(
                parcours.getId()).orElseThrow(()
                -> new RuntimeException(String.format(
                        "Cannot Find Parcours by ID %s", parcours.getId())));
        parcoursModify.setDescription(parcours.getDescription());

        parcoursRepository.save(parcoursModify);
    }

    /**
     * @return le repository associé au service.
     */
    public ParcoursRepository getParcoursRepository() {
        return this.parcoursRepository;
    }

}
