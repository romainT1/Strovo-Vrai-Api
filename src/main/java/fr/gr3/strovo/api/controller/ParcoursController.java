package fr.gr3.strovo.api.controller;

import fr.gr3.strovo.api.model.Filter;
import fr.gr3.strovo.api.model.Parcours;
import fr.gr3.strovo.api.model.Token;
import fr.gr3.strovo.api.service.ParcoursService;
import fr.gr3.strovo.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur pour la gestion des parcours.
 */
@RestController
@RequestMapping("/parcours")
public class ParcoursController {

    @Autowired
    private TokenService tokenService;

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
     * @return une réponse http et le parcours ajouté :
     * <ul>
     *     <li>code 201 CREATED - si parcours ajouté</li>
     *     <li>code 403 FORBIDDEN - si token invalide</li>
     * </ul>
     */
    @PostMapping
    public ResponseEntity<Parcours> addParcours(@RequestBody final Parcours parcours,
                                      @RequestHeader("Authorization") final String token) {
        Token tokenAuth = new Token(token);
        if (tokenService.isValidToken(tokenAuth)) {
            String parcoursId = UUID.randomUUID().toString();
            int userId = tokenService.getUserIdFromToken(tokenAuth);

            parcours.setId(parcoursId);
            parcours.setUserId(userId);
            parcoursService.addParcours(parcours);

            return new ResponseEntity<>(parcours, HttpStatus.CREATED);
        }
        
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * Récupère un parcours par son identifiant.
     *
     * @param parcoursId Parcours à récupérer.
     * @return ResponseEntity avec le statut HTTP correspondant, 
     * et le parcours trouvé.
     * <ul>
     *     <li>code 200 OK - si parcours trouvé</li>
     *     <li>code 204 NO CONTENT - si aucun parcours trouvé</li>
     *     <li>code 403 FORBIDDEN - si token invalide ou accès interdit</li>
     * </ul>
     */
    @GetMapping("/{parcoursId}")
    public ResponseEntity<Parcours> getParcoursById(
                                @PathVariable final String parcoursId,
                                @RequestHeader("Authorization") final String token) {
        Token tokenAuth = new Token(token);
        if (tokenService.isValidToken(tokenAuth)) {
            int userId = tokenService.getUserIdFromToken(tokenAuth);

            Parcours parcours = parcoursService.getParcoursById(parcoursId);
            if (parcours == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            if (parcours.getUserId() == userId) {
                return new ResponseEntity<>(
                    parcoursService.getParcoursById(parcoursId), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * Récupère la liste des parcours d'un utilisateur.
     *
     * @return ResponseEntity avec le statut HTTP correspondant
     * et la liste des parcours trouvés.
     * <ul>
     *     <li>code 200 OK - si parcours trouvé</li>
     *     <li>code 204 NO CONTENT - si parcours aucun trouvé</li>
     *     <li>code 403 FORBIDDEN - si token invalide</li>
     * </ul>
     */
    @GetMapping
    public ResponseEntity<List<Parcours>> getParcours(
            @RequestHeader("Authorization") final String token) {

        Token tokenAuth = new Token(token);
        if (tokenService.isValidToken(tokenAuth)) {
            int userId = tokenService.getUserIdFromToken(tokenAuth);

            List<Parcours> parcoursList =
                    parcoursService.getParcoursByUserId(userId);

            if (parcoursList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(parcoursList,  HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * Supprime un parcours.
     *
     * @param parcoursId Identifiant du parcours.
     * @return ResponseEntity avec le statut HTTP correspondant
     * et le parcours supprimé
     * <ul>
     *     <li>code 200 OK - si parcours supprimé</li>
     *     <li>code 204 NO CONTENT - si parcours aucun trouvé</li>
     *     <li>code 403 FORBIDDEN - si token invalide ou accès interdit</li>
     * </ul>
     */
    @DeleteMapping("/{parcoursId}")
    public ResponseEntity deleteParcours(
            @PathVariable final String parcoursId,
            @RequestHeader("Authorization") final String token) {
        Token tokenAuth = new Token(token);
        if (tokenService.isValidToken(tokenAuth)) {
            int userId = tokenService.getUserIdFromToken(tokenAuth);

            Parcours parcours = parcoursService.getParcoursById(parcoursId);
            if (parcours == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            if (parcours.getUserId() == userId) {
                parcoursService.deleteParcours(parcoursId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
            @RequestBody final Parcours parcours,
            @RequestHeader("Authorization") final String token) {
        Token tokenAuth = new Token(token);
        if (tokenService.isValidToken(tokenAuth)) {
            int userId = tokenService.getUserIdFromToken(tokenAuth);

            if (parcours.getUserId() == userId) {
                parcoursService.updateParcours(parcours);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
