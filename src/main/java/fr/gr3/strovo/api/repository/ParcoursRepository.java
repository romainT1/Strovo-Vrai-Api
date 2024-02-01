package fr.gr3.strovo.api.repository;

import fr.gr3.strovo.api.model.Parcours;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface décrivant un ParcoursReposiroty.
 */
public interface ParcoursRepository extends MongoRepository<Parcours, String>, CustomParcoursRepository{

}
