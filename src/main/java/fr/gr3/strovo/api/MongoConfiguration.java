package fr.gr3.strovo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * Classe de configuration pour MongoDB dans une application Spring.
 */
@Configuration
public class MongoConfiguration {

    /** Injection de dépendance MongoDatabaseFactory. */
    @Autowired
    private MongoDatabaseFactory mongoDbFactory;

    /** Injection de dépendance MongoMappingContext. */
    @Autowired
    private MongoMappingContext mongoMappingContext;

    /**
     * Configure et fournit un bean MappingMongoConverter.
     * Le MappingMongoConverter est responsable de la conversion
     * des documents MongoDB en objets Java et vice versa.
     * Il utilise le MongoDatabaseFactory et le MongoMappingContext fournis.
     * @return Bean MappingMongoConverter configuré
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter =
                new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}
