package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.repository.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * @return le parcours ajouté
     */
    public Parcours addParcours(final Parcours parcours) {
        return parcoursRepository.insert(parcours);
    }

    /**
     * Récupère un parcours par son Id.
     *
     * @param parcoursId Identifiant du parcours à rechercher.
     * @return le parcours correspondant à l'identifiant
     */
    public Parcours getParcoursById(final String parcoursId) {
        Optional<Parcours> parcours = parcoursRepository.findById(parcoursId);
        return parcours.isPresent() ? parcours.get() : null;
    }

    /**
     * Récupère la liste des parcours d'un utilisateur.
     *
     * @param userId Identifiant de l'utilisateur.
     * @return les parcours associés à l'utilisateur et aux filtres
     */
    public List<Parcours> getParcoursByUserId(final int userId) {
        return parcoursRepository.findAllByUserId(userId);
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
     * Modifie un parcours.
     *
     * @param parcours parcours a modifier.
     * @return le parcours modifié
     */
    public Parcours updateParcours(final Parcours parcours) {
        return parcoursRepository.save(parcours);
    }

}
