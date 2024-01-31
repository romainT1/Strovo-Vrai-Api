package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Récupère parcours par son identifiant.
     *
     * @param parcoursId Parcours à récupérer.
     * @return ResponseEntity avec le statut HTTP correspondant.
     */
    @GetMapping("/{parcoursId}")
    public ResponseEntity getParcoursByParcoursId(@PathVariable String parcoursId) {
        return ResponseEntity.ok(parcoursService.getParcoursById(parcoursId));
    }
}
