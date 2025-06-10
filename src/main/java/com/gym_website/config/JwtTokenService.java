package com.gym_website.config;

import com.gym_website.util.enums.EAppRoles;
import com.gym_website.util.exceptions.AuthenticationCredentialsNotFoundException;
import com.gym_website.util.exceptions.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class JwtTokenService {
    private static final long EXPIRATIONTIME = Duration.ofDays(3).toMillis();
    private static final String HEADER_STRING = "app-auth";
    private static final String CLAIM_USERNAME = "username";
    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_ROLES = "roles";
    @Value("${application.secret}")
    private String secret;

    public Authentication getAuthentication(final HttpServletRequest request) {
        Authentication auth = null;
        final String token = Optional.ofNullable(request.getHeader(HEADER_STRING)).orElse(null);

        try {
            // Check if token exists and is not empty
            if (token == null || token.isEmpty()) {
                return null;
            }

            // Parse the token
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            if (claims == null) {
                return null;
            }

            // Extract username
            final String username = Optional
                    .ofNullable(claims.getBody().get(CLAIM_USERNAME))
                    .map(Object::toString)
                    .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("No username given in jwt"));

            // Extract user ID (may be null for tokens created before this change)
            final String userId = Optional
                    .ofNullable(claims.getBody().get(CLAIM_USER_ID))
                    .map(Object::toString)
                    .orElse(null);

            // Extract role
            String role = Optional
                    .ofNullable(claims.getBody().get(CLAIM_ROLES))
                    .map(Object::toString)
                    .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("No roles given in jwt"));

            // Create authorities list
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            // Use userId as principal if available, otherwise use username
            String principal = userId != null ? userId : username;
            auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);

        } catch (final MalformedJwtException | UnsupportedJwtException ex) {
            log.error("Unsupported jwt token {} with exception {}",
                    token,
                    ex.getMessage());
            throw new JwtAuthenticationException(ex);
        } catch (final ExpiredJwtException ex) {
            log.error("Expired jwt token {}",
                    ex.getMessage());
            throw new JwtAuthenticationException(ex);
        } catch (final AuthenticationCredentialsNotFoundException ex) {
            log.error("An error occurred while trying to create authentication based on jwt token, missing credentials {}",
                    ex.getMessage());
            throw new JwtAuthenticationException(ex);
        } catch (final Exception ex) {
            log.error("Unexpected exception occurred while parsing jwt {} exception: {}",
                    token,
                    ex.getMessage());
            throw new JwtAuthenticationException(ex);
        }

        log.debug("The authentication constructed by the JwtService");
        return auth;
    }

    public String createJwtToken(final String username, final Long userId, final EAppRoles role) {
        // create the jwt token
        String jwtToken;

        jwtToken = Jwts.builder()
                .claim(CLAIM_USERNAME, username)
                .claim(CLAIM_USER_ID, userId)
                .claim(CLAIM_ROLES, role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(getSigningKey())
                .compact();
        return jwtToken;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
