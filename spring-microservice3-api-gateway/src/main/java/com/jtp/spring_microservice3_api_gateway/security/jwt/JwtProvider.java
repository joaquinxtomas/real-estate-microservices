package com.jtp.spring_microservice3_api_gateway.security.jwt;

import com.jtp.spring_microservice3_api_gateway.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest req);
}
