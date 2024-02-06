package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.InterestPointService;
import fr.gr3.strovo.api.service.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getParcoursByParcoursId(@PathVariable String parcoursId) {
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
    public ResponseEntity getParcoursByUserId(@PathVariable int userId,
                                              @RequestParam(required = false) String nom,
                                              @RequestParam(required = false) Date dateDebut,
                                              @RequestParam(required = false) Date dateFin
    ) {
        Filter filter = null;
        if (nom != null || dateDebut != null || dateFin != null) {
            filter = new Filter(nom, dateDebut, dateFin);
        }

        List<Parcours> parcoursList = parcoursService.getParcoursByUserIdAndFilters(userId, filter);
        return ResponseEntity.ok(parcoursList);
    }

    @DeleteMapping("/{parcoursId}")
    public ResponseEntity deleteParcours(@PathVariable String parcoursId) {
        Parcours parcours = parcoursService.getParcoursById(parcoursId);
        parcoursService.deleteParcours(parcoursId);

        String[] interestPointId = parcours.getInterestPointsIds();
        for (String id : interestPointId) {
            interestPointService.deleteInterestPoint(id);
        }
        return ResponseEntity.noContent().build();
    }
}
