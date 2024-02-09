package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.InterestPointService;
import fr.gr3.strovo.api.service.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.Date;
import java.util.List;

/**
 * Contrôleur pour la gestion des parcours.
 */
@RestController
@RequestMapping("/parcours")
public class ParcoursController {

    /**
     * Service pour la gestion des parcours.
     */
    @Autowired
    private ParcoursService parcoursService;

    /**
     * Service pour la gestion des points d'interet.
     */
    @Autowired
    private InterestPointService interestPointService;

    /**
     * Constructeur du contrôleur.
     */
    public ParcoursController() {
    }

    /**
     * Ajoute un nouveau parcours.
     *
     * @param parcours Parcours à ajouter.
     * @return ResponseEntity avec le statut HTTP correspondant.
     */
    @PostMapping
    public ResponseEntity addParcours(@RequestBody final Parcours parcours) {
        parcoursService.addParcours(parcours);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Récupère un parcours par son identifiant.
     *
     * @param parcoursId Parcours à récupérer.
     * @return ResponseEntity avec le statut HTTP correspondant.
     */
    @GetMapping("/{parcoursId}")
    public ResponseEntity getParcoursByParcoursId(
                                @PathVariable final String parcoursId) {
        return ResponseEntity.ok(parcoursService.getParcoursById(parcoursId));
    }

    /**
     * Récupère la liste des parcours d'un utilisateur avec des filtres.
     *
     * @param userId Identifiant de l'utilisateur.
     * @param nom nom du parcours(non requis).
     * @param dateDebut date de début du parcours(non requis).
     * @param dateFin date de fin du parcours(non requis).
     * @return ResponseEntity avec le statut HTTP correspondant.
     */
    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity getParcoursByUserId(
            @PathVariable final int userId,
            @RequestParam(required = false) final String nom,
            @RequestParam(required = false) final String dateDebut,
            @RequestParam(required = false) final String dateFin) {
      
        Filter filter = null;
        if (nom != null || dateDebut != null || dateFin != null) {
            filter = new Filter(nom, dateDebut, dateFin);
        }

        List<Parcours> parcoursList =
                parcoursService.getParcoursByUserIdAndFilters(userId, filter);
        return ResponseEntity.ok(parcoursList);
    }

    /**
     * Supprime un parcours.
     *
     * @param parcoursId Identifiant du parcours.
     * @return ResponseEntity avec le statut HTTP "No Content"
     * (204) si la suppression est réussie.
     */
    @DeleteMapping("/{parcoursId}")
    public ResponseEntity deleteParcours(
            @PathVariable final String parcoursId) {
        Parcours parcours = parcoursService.getParcoursById(parcoursId);
        parcoursService.deleteParcours(parcoursId);

        String[] interestPointId = parcours.getInterestPointsIds();
        for (String id : interestPointId) {
            interestPointService.deleteInterestPoint(id);
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Met à jour les informations d'un parcours existant.
     *
     * @param parcours Parcours contenant les nouvelles informations
     * à mettre à jour.
     * @return ResponseEntity avec le statut HTTP "OK" (200)
     * si la mise à jour est réussie.
     */
    @PutMapping
    public ResponseEntity updateParcours(
            @RequestBody final Parcours parcours) {
        parcoursService.updateParcours(parcours);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
