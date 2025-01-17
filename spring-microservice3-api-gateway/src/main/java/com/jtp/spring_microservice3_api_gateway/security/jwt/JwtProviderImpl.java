package com.jtp.spring_microservice3_api_gateway.security.jwt;

import com.jtp.spring_microservice3_api_gateway.security.UserPrincipal;
import com.jtp.spring_microservice3_api_gateway.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProviderImpl implements JwtProvider{

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-ms}")
    private String JWT_EXPIRATION;

    @Override
    public String generateToken(UserPrincipal auth){
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(auth.getUsername())
                .claim("roles",authorities)
                .claim("userId", auth.getId())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest req){
        Claims claims = extractClaims(req);
        if(claims == null){
            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = UserPrincipal.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    public boolean isTokenValid(HttpServletRequest req){
        Claims claims = extractClaims(req);
        if(claims == null){
            return false;
        }

        if(claims.getExpiration().before(new Date())){
            return false;
        }

        return true;
    }

    public Claims extractClaims(HttpServletRequest req){
        String token = SecurityUtils.extractAuthTokenFromRequest(req);

        if(token == null){
            return null;
        }

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
