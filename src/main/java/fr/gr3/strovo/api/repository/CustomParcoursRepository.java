package fr.gr3.strovo.api.repository;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;

import java.util.List;

/**
 * Interface décrivant un CustomParcoursRepository.
 */
public interface CustomParcoursRepository {

    /**
     * Récupère la liste des parcours d'un utilisateur
     * avec des filtres spécifiques.
     *
     * @param userId Identifiant de l'utilisateur.
     * @return les parcours associés à l'utilisateur et aux filtres.
     * @throws IllegalArgumentException Si l'ID de l'utilisateur est négatif
     * ou si le filtre est invalide.
     */
    List<Parcours> findAllByUserId(int userId);
}
