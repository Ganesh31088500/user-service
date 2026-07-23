package com.apiwatch.user_service.security;



import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final com.apiwatch.user_service.config.JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // No Authorization header
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {

            if (!jwtService.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtService.extractAllClaims(token);

            String username = claims.getSubject();

            Collection<SimpleGrantedAuthority> authorities =
                    extractAuthorities(claims);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            log.debug("Authenticated User : {}", username);

        } catch (Exception ex) {

            log.error("JWT Authentication Failed : {}", ex.getMessage());

            SecurityContextHolder.clearContext();

        }

        filterChain.doFilter(request, response);
    }

    @SuppressWarnings("unchecked")
    private Collection<SimpleGrantedAuthority> extractAuthorities(
            Claims claims) {

        Object rolesObject = claims.get("roles");

        if (rolesObject == null) {
            return Collections.emptyList();
        }

        if (rolesObject instanceof List<?> roles) {

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.toString()))
                    .toList();
        }

        return Collections.emptyList();
    }
}