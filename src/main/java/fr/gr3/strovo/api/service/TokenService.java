package fr.gr3.strovo.api.service;

import java.util.Date;
import java.util.UUID;

import fr.gr3.strovo.api.model.Token;
import fr.gr3.strovo.api.model.User;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Service pour la gestion des tokens.
 */
@Service
public class TokenService {

    /** Clé de chiffrement du token. */
    private static final String KEY = "test"; // TODO changer

    /** Algorithme de chiffrement du token. */
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(generate256SecretKey());

    /** Champ identifiant dans le token. */
    private static final String ID_KEY = "id";

    /**
     * Génère une clé de 64 caractères (256bits).
     * @return la clé générée
     */
    private static String generate256SecretKey() {
        String key = "";
        for (int i = 0; i < 4; i++) {
            key += UUID.randomUUID().toString();
        }
        key = key.replaceAll("-", "");
        return key;
    }

    /**
     * Génère un token à partir de l'algotihme HMAC256.
     * Le token contient l'identifiant de l'utilisateur, il expire sous 24h.
     * @param user utilisateur demandant le token
     * @param expireIn millisecondes de validité avant expiration
     * @return un token utilisable par l'utilisateur
     */
    public Token generateToken(final User user, final int expireIn) {
        long tokenLifeTimeMillis = expireIn;
        String token = JWT.create()
                .withClaim(ID_KEY, String.valueOf(user.getId()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + tokenLifeTimeMillis))
                .withJWTId(UUID.randomUUID().toString())
                .sign(ALGORITHM);
        return new Token(token);
    }

    /**
     * Vérifie la validité du token d'un utilisateur.
     * Un token est considéré comme valide si :
     * <ul>
     *     <li>Sa date d'expiration n'est pas dépassée</li>
     *     <li>Il n'a pas été modifié</li>
     * </ul>
     * @param token valeur du token à vérifier
     * @return true si le token est valide, false sinon
     */
    public boolean isValidToken(final Token token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            verifier.verify(token.getValue());
        } catch (JWTVerificationException jwtVerificationException) {
            return false;
        }
        return true;
    }

    /**
     * Récupère l'identifiant utilisateur stocké dans le token.
     * @param token token utilisateur en question
     * @return l'indentifiant trouvé
     */
    public int getUserIdFromToken(final Token token) {
        DecodedJWT jwt = JWT.require(ALGORITHM).build()
        .verify(token.getValue());

        Claim id = jwt.getClaim(ID_KEY);
        return Integer.parseInt(id.asString());
    }
}
