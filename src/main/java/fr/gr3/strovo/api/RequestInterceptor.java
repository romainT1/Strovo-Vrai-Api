package fr.gr3.strovo.api;


import java.util.Arrays;
import java.util.List;

import fr.gr3.strovo.api.model.Token;
import org.springframework.web.servlet.HandlerInterceptor;

import fr.gr3.strovo.api.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Intercepteur de toutes les requêtes API.
 */
public class RequestInterceptor implements HandlerInterceptor {
    
    /** Points de terminaison ne nécessitant pas d'authentification. */
    private static final String[] NO_AUTHENTIFICATION_ENDPOINTS = {
        "/user/signup",
        "/user/login",
        "/error"
    };

    /** Service pour la gestion des tokens. */
    private TokenService tokenService;
    
    /**
     * Crée une instance RequestInterceptor.
     */
    public RequestInterceptor() {
        tokenService = new TokenService();
    }

    /**
     * Exécuté avant chaque requête API.
     * Vérifie si la requête effectuée nécessite une authentification.
     * Si une authentification est nécessaire, vérifie la présence et validité
     * du token.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        boolean isAuthorized = true;

        if (authentificationNeeded(request)) {
            Token token = new Token(extractTokenFromRequest(request));

            if (! tokenService.isValidToken(token)) {
                isAuthorized = false;
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        return isAuthorized;
    }

    /**
     * Extrait le token présent dans une requête.
     * @param request requête en question
     * @return le token si présent, null sinon
     */
    private String extractTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * Vérifie si une requête nécessite une authentification.
     * @param request requête en question
     * @return true si nécessite une authentification, false sinon
     */
    private boolean authentificationNeeded(HttpServletRequest request) {
        List<String> noAuthList = Arrays.asList(NO_AUTHENTIFICATION_ENDPOINTS);
        System.out.println(request.getRequestURI());
        return ! noAuthList.contains(request.getRequestURI());
    }
}
