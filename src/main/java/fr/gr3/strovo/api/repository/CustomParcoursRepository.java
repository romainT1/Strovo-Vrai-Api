package fr.gr3.strovo.api.repository;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;

import java.util.List;

/**
 * Interface décrivant un CustomParcoursRepository.
 */
public interface CustomParcoursRepository {

    /**
     * Récupère la liste des parcours d'un utilisateur.
     *
     * @param userId Identifiant de l'utilisateur.
     * @param filter Filtre correspondant à la recherche.
     * @return les parcours associés à l'utilisateur et aux filtres.
     */
    List<Parcours> findAllByUserIdAndFilters(int userId, Filter filter);
}
