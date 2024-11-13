package com.ideologyCreativeStudio.test.config;

import com.ideologyCreativeStudio.test.businesslayer.security.SecurityUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${jwt.key}")
    private String securityKey;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.refreshExpirationMs}")
    private long refreshExpirationMs;

    private SecretKey getSigningKey() {
        byte[] keyBytes = securityKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(Authentication auth) {
        SecurityUserDetails user = (SecurityUserDetails) auth.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setIssuer(issuer)
                .setExpiration(new Date(new Date().getTime() + expirationMs))
                .claim("roles", user.getAuthorities().stream()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .collect(Collectors.toList()))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(Authentication auth) {
        SecurityUserDetails user = (SecurityUserDetails) auth.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setIssuer(issuer)
                .setExpiration(new Date(new Date().getTime() + refreshExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isTokenValid(String token) throws ExpiredJwtException {
        try {
            token = token.trim();
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .requireIssuer(issuer)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            // Firma non valida
            System.err.println("Firma non valida: " + e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            // Token malformato
            System.err.println("Token malformato: " + e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            // Tipo di token non supportato
            System.err.println("Tipo di token non supportato: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            // Token nullo o vuoto
            System.err.println("Token nullo o vuoto: " + e.getMessage());
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        token = token.trim();
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            System.err.println("Errore durante l'estrazione del nome utente: " + e.getMessage());
            return null;
        }
    }

    public List<String> getRolesFromToken(String token) {
        token = token.trim();
        try {
            return (List<String>) Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("roles");  // Estrai il campo "roles"
        } catch (JwtException e) {
            System.err.println("Errore durante l'estrazione dei ruoli: " + e.getMessage());
            return null;
        }
    }

    public String refreshAccessToken(String refreshToken) {
        String username = getUsernameFromToken(refreshToken);

        // Verifica se il refresh token è valido
        if (isTokenValid(refreshToken)) {
            return generateAccessToken(new UsernamePasswordAuthenticationToken(username, null, List.of()));
        } else {
            throw new JwtException("Refresh token non valido o scaduto.");
        }
    }
}
