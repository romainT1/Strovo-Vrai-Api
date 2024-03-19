package fr.gr3.strovo.api.repository;

import fr.gr3.strovo.api.model.Parcours;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

/**
 * Implémentation personnalisée de l'interface {@link CustomParcoursRepository}
 * pour l'interaction avec la base de données MongoDB.
 */
public class CustomParcoursRepositoryImpl implements CustomParcoursRepository {

    /**
     * Template Spring Data MongoDB permettant d'effectuer
     * des opérations sur la base de données MongoDB.
     */
    private final MongoTemplate mongoTemplate;

    /**
     * Constructeur de la classe CustomParcoursRepositoryImpl.
     *
     * @param mongoTemplateRepo Instance de {@link MongoTemplate}
     *                      utilisée pour interagir avec
     *                      la base de données MongoDB.
     */
    public CustomParcoursRepositoryImpl(final MongoTemplate mongoTemplateRepo) {
        this.mongoTemplate =
                new MongoTemplate(mongoTemplateRepo.getMongoDatabaseFactory());
    }

    /**
     * Récupère la liste des parcours d'un utilisateur
     * avec des filtres spécifiques.
     *
     * @param userId Identifiant de l'utilisateur.
     * @return les parcours associés à l'utilisateur et aux filtres.
     */
    @Override
    public List<Parcours> findAllByUserId(final int userId) {
        Query query = new Query();

        Criteria criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);

        return mongoTemplate.find(query, Parcours.class);
    }
}
