package fr.gr3.strovo.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.gr3.strovo.api.model.Token;
import fr.gr3.strovo.api.model.User;
import fr.gr3.strovo.api.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.HandlerInterceptor;

public class RequestInterceptorTest {

    private RequestInterceptor requestInterceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;
    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        tokenService = new TokenService();
        requestInterceptor = new RequestInterceptor();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        handler = new Object();
    }

    @Test
    public void testPreHandle_noAuthNeeded() throws Exception {
        when(request.getRequestURI()).thenReturn("/user/signup");
        assertTrue(requestInterceptor.preHandle(request, response, handler));
    }

    @Test
    public void testPreHandle_authNeeded_tokenValid() throws Exception {
        User user = new User();
        user.setId(1);
        Token validToken = tokenService.generateToken(user, 10000);

        when(request.getRequestURI()).thenReturn("/user/profile");
        when(request.getHeader("Authorization")).thenReturn(validToken.getValue());
        assertTrue(requestInterceptor.preHandle(request, response, handler));
    }

    @Test
    public void testPreHandle_authNeeded_tokenInvalid() throws Exception {
        when(request.getRequestURI()).thenReturn("/user/profile");
        when(request.getHeader("Authorization")).thenReturn("invalidToken");
        assertFalse(requestInterceptor.preHandle(request, response, handler));
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
