package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.service.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    private final ParcoursService parcoursService;

    /**
     * Constructeur du contrôleur.
     *
     * @param parcoursService Service pour la gestion des parcours à injecter.
     */
    public ParcoursController(final ParcoursService parcoursService) {
        this.parcoursService = new ParcoursService(parcoursService);
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

}
