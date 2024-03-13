package fr.gr3.strovo.api;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.
        builders.HttpSecurity;
import org.springframework.security.config.annotation.web.
        configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de la sécurité de l'API
 */
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Fonction qui permet de désactiver la sécurité de base de Spring Security
     * car nous utilisons un système de token JWT.
     * @param http
     * @return un objet SecurityFilterChain qui est utilisé
     *         par Spring Security pour appliquer la sécurité
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        return http.build();
    }
}


