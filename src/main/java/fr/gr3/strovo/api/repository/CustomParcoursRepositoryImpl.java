package fr.gr3.strovo.api.repository;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
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
     * @param filter Filtre correspondant à la recherche.
     * @return les parcours associés à l'utilisateur et aux filtres.
     */
    @Override
    public List<Parcours> findAllByUserIdAndFilters(final int userId,
                                                    final Filter filter) {
        Query query = new Query();

        Criteria criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);

        if (filter != null) {
            String nameParcours = filter.getNameParcours();

            String startDate = filter.getStartDate();
            String endDate = filter.getEndDate();

            if (nameParcours != null) {
                criteria.and("name").is(filter.getNameParcours());
            }

            if (startDate != null && endDate != null) {
                long startTimestamp = Long.parseLong(startDate);
                long endTimestamp = Long.parseLong(endDate);

                Date start = new Date(startTimestamp);
                Date end = new Date(endTimestamp);

                criteria.and("date").gte(start).lte(end);
            } else if (startDate != null) {
                long startTimestamp = Long.parseLong(startDate);
                Date start = new Date(startTimestamp);

                criteria.and("date").gte(start);
            } else if (endDate != null) {
                long endTimestamp = Long.parseLong(endDate);
                Date end = new Date(endTimestamp);

                criteria.and("date").lte(end);
            }
        }

        return mongoTemplate.find(query, Parcours.class);
    }
}
