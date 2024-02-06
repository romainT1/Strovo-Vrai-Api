package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.InterestPoint;
import fr.gr3.strovo.api.repository.InterestPointRepository;
import org.springframework.stereotype.Service;

/**
 * Service pour gérer les points d'intérêt.
 */
@Service
public class InterestPointService {
    private final InterestPointRepository interestPointRepository;

    /**
     * Constructeur pour le service des points d'intérêt.
     * @param interestPointRepository Le dépôt pour gérer les points d'intérêt.
     */
    public InterestPointService(InterestPointRepository interestPointRepository) {
        this.interestPointRepository = interestPointRepository;
    }

    /**
     * Méthode pour ajouter un point d'intérêt.
     * @param interestPoint Le point d'intérêt à ajouter.
     */
    public void addInterestPoint(InterestPoint interestPoint) {
        interestPointRepository.insert(interestPoint);
    }

    public void deleteInterestPoint(String interestPointId) {
        interestPointRepository.deleteById(interestPointId);
    }

    public InterestPoint getInterestPointById(String interestPointId) {
        return interestPointRepository.findById(interestPointId).orElseThrow(()
                -> new RuntimeException(String.format("Cannot Find Interest Point by Id - %s", interestPointId)));
    }

}
