package com.emazon.transaction.infraestructure.configuration.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "3nKAe0ytwn6XSOf/7mI7mmyiRrdVvcl4YVy9kG6ChaI=";
    private final String CLAIM_KEY_ROLE = "role";
    private final String CLAIM_KEY_SUBJECT = "sub";

    public Long extractUserId(String token) {
        final Claims claims = extractAllClaims(token);
        String subject = (String) claims.get(CLAIM_KEY_SUBJECT);
        return Long.valueOf(subject);
    }

    public String extractRole(String token) {
        final Claims claims = extractAllClaims(token);
        return (String) claims.get(CLAIM_KEY_ROLE);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        byte[] secret = SECRET_KEY.getBytes();

        return Jwts.parser()
                .setSigningKey(secret)
                .build().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        boolean expired = extractExpiration(token).before(new Date());
        return expired;
    }

    public Boolean validateToken(String token, CustomUserDetails userDetails) {
        final Long userId = extractUserId(token);
        boolean equalsuser = Objects.equals(userId, userDetails.getUserId());
        boolean response = equalsuser && !isTokenExpired(token);
        return response;
    }
}
