package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.InterestPoint;
import fr.gr3.strovo.api.service.InterestPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur pour gérer les points d'intérêt.
 */
@RestController
@RequestMapping("/interestPoint")
public class InterestPointController {
    /**
     * Le service pour gérer les points d'intérêt.
     */
    private final InterestPointService service;

    /**
     * Constructeur pour le contrôleur des points d'intérêt.
     *
     * @param serviceInterestPoint Le service pour gérer les points d'intérêt.
     */
    public InterestPointController(
            final InterestPointService serviceInterestPoint) {
        this.service = serviceInterestPoint;
    }

    /**
     * Méthode pour ajouter un point d'intérêt.
     * @param point Le point d'intérêt à ajouter.
     * @return Une réponse indiquant que le point d'intérêt a été créé.
     */
    @PostMapping()
    public ResponseEntity addInterestPoint(
            @RequestBody final InterestPoint point) {
        service.addInterestPoint(point);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
