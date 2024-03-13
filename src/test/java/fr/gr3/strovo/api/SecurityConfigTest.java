package fr.gr3.strovo.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@EnableWebSecurity
public class SecurityConfigTest {

    @Test
    public void testSecurityFilterChain() throws Exception {
        HttpSecurity httpSecurity = Mockito.mock(HttpSecurity.class);
        DefaultSecurityFilterChain securityFilterChain = Mockito.mock(DefaultSecurityFilterChain.class);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = Mockito.mock(ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry.class);
        AuthorizedUrl authorizedUrl = Mockito.mock(AuthorizedUrl.class);

        when(httpSecurity.authorizeRequests()).thenReturn(registry);
        when(registry.anyRequest()).thenReturn(authorizedUrl);
        when(authorizedUrl.permitAll()).thenReturn(registry);
        when(httpSecurity.build()).thenReturn(securityFilterChain);

        SecurityConfig securityConfig = new SecurityConfig();
        securityConfig.securityFilterChain(httpSecurity);

        verify(httpSecurity).authorizeRequests();
        verify(registry).anyRequest();
        verify(authorizedUrl).permitAll();
        verify(httpSecurity).build();
    }
}
