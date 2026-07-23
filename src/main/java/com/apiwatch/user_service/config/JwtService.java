package com.apiwatch.user_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final JwtProperties jwtProperties;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {

        byte[] keyBytes =
                Base64.getDecoder().decode(jwtProperties.getSecret());

        signingKey = Keys.hmacShaKeyFor(keyBytes);

    }

    /**
     * Parse JWT and return all claims
     */
    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(signingKey)
                .requireIssuer(jwtProperties.getIssuer())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    /**
     * Generic claim extractor
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);

    }

    /**
     * Email/Username
     */
    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );

    }

    /**
     * Expiration date
     */
    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );

    }

    /**
     * Check expiry
     */
    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());

    }

    /**
     * Validate JWT
     */
    public boolean validateToken(String token) {

        try {

            Claims claims = extractAllClaims(token);

            return claims.getExpiration()
                    .after(new Date());

        } catch (JwtException ex) {

            log.error("Invalid JWT : {}", ex.getMessage());

            return false;

        } catch (Exception ex) {

            log.error("JWT Validation Failed : {}", ex.getMessage());

            return false;

        }

    }

    /**
     * Extract userId claim (optional)
     */
    public String extractUserId(String token) {

        return extractClaim(
                token,
                claims -> claims.get("userId", String.class)
        );

    }

    /**
     * Extract roles
     */
    public Object extractRoles(String token) {

        return extractClaim(
                token,
                claims -> claims.get("roles")
        );

    }

}
