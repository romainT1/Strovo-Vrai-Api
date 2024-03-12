package fr.gr3.strovo.api.repository;

import fr.gr3.strovo.api.model.InterestPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Dépôt pour gérer les points d'intérêt.
 */
@Repository
public interface InterestPointRepository
        extends MongoRepository<InterestPoint, String> {
}
