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
public class CustomParcoursRepositoryImpl implements CustomParcoursRepository{

    /**
     * Template Spring Data MongoDB permettant d'effectuer
     * des opérations sur la base de données MongoDB.
     */
    private final MongoTemplate mongoTemplate;

    /**
     * Constructeur de la classe CustomParcoursRepositoryImpl.
     *
     * @param mongoTemplate Instance de {@link MongoTemplate}
     *                      utilisée pour interagir avec
     *                      la base de données MongoDB.
     */
    public CustomParcoursRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Parcours> findAllByUserIdAndFilters(int userId, Filter filter) {
        Query query = new Query();

        Criteria criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);

        if (filter != null) {
            String nameParcours = filter.getNameParcours();
            Date startDate = filter.getStartDate();
            Date endDate = filter.getEndDate();

            if (nameParcours != null) {
                criteria.and("name").is(filter.getNameParcours());
            }

            if (startDate != null && endDate != null) {
                criteria.and("date").gte(startDate).lte(endDate);
            }
        }

        return mongoTemplate.find(query, Parcours.class);
    }
}
