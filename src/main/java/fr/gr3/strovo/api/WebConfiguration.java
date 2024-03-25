package fr.gr3.strovo.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration web de l'api.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Ajoute les intercepteurs de requêtes.
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }
}
