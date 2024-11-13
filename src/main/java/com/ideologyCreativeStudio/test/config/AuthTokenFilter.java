package com.ideologyCreativeStudio.test.config;

import com.ideologyCreativeStudio.test.businesslayer.security.ApplicationsUserDetailsService;
import com.ideologyCreativeStudio.test.businesslayer.security.SecurityUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ApplicationsUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Recupera l'header Authorization dalla richiesta
        String authorizationHeader = request.getHeader("Authorization");
        // Controlla se l'header Authorization è presente e inizia con "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);  // Estrai il token JWT (dopo "Bearer ")
            try {
                // Verifica la validità del token
                if (jwtUtils.isTokenValid(token)) {
                    // Estrai il nome utente dal token
                    String username = jwtUtils.getUsernameFromToken(token);
                    log.info("Username from Token: {}", username);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    log.info("UserDetails: {}", userDetails.getAuthorities());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("Authentication set for user: {}", username);
                }
            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token expired");
                return;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Invalid token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
