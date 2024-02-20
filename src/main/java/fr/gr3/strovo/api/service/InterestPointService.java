package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.InterestPoint;
import fr.gr3.strovo.api.repository.InterestPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service pour gérer les points d'intérêt.
 */
@Service
public class InterestPointService {

    /**
     * Repository pour l'accès aux données des points d'interet.
     */
    @Autowired
    private InterestPointRepository interestPointRepository;

    /**
     * Constructeur du service.
     */
    public InterestPointService() {
    }

    /**
     * Construit un nouveau InterestPointService en copiant les champs
     * de l'instance originale de InterestPointService fourni.
     *
     * @param original L'instance originale de InterestPointService à copier.
     */
    public InterestPointService(final InterestPointService original) {
        this.interestPointRepository = original.interestPointRepository;
    }

    /**
     * Associe le repository au service.
     *
     * @param interestPointRepositoryImpl le repository.
     */
    public void setInterestPointRepository(final InterestPointRepository
                                              interestPointRepositoryImpl) {
        this.interestPointRepository = interestPointRepositoryImpl;
    }
    /**
     * Méthode pour ajouter un point d'intérêt.
     * @param interestPoint Le point d'intérêt à ajouter.
     */
    public void addInterestPoint(final InterestPoint interestPoint) {
        interestPointRepository.insert(interestPoint);
    }

    /**
     * Méthode pour supprimer un point d'intérêt.
     * @param interestPointId Le point d'intérêt à supprimer.
     */
    public void deleteInterestPoint(final String interestPointId) {
        interestPointRepository.deleteById(interestPointId);
    }


    /**
     * Méthode pour récupérer un point d'intérêt.
     * @param interestPointId Le point d'intérêt à supprimer.
     * @return le point d'intéret
     */
    public InterestPoint getInterestPointById(final String interestPointId) {
        return interestPointRepository.findById(interestPointId).orElseThrow(()
                -> new RuntimeException(
                        String.format(
                                "Cannot Find Interest Point by Id - %s",
                                interestPointId)));
    }

    /**
     * @return le repository associé au service.
     */
    public InterestPointRepository getInterestPointRepository() {
        return this.interestPointRepository;
    }

}


