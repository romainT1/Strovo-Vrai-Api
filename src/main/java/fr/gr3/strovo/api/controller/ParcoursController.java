package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.ParcoursService;
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
    private final ParcoursService parcoursService;

    /**
     * Constructeur du contrôleur.
     *
     * @param parcoursService      Service pour la gestion des parcours à injecter.
     */
    public ParcoursController(ParcoursService parcoursService) {
        this.parcoursService = parcoursService;
    }

    /**
     * Ajoute un nouveau parcours.
     *
     * @param parcours Parcours à ajouter.
     * @return ResponseEntity avec le statut HTTP correspondant.
     */
    @PostMapping
    public ResponseEntity addParcours(@RequestBody Parcours parcours) {
        parcoursService.addParcours(parcours);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}