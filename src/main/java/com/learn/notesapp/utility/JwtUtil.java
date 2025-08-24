package com.learn.notesapp.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {


    // Secret key for singing jwt
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // token validity 10 mins
    private final long JWT_EXPIRATION = 2 * 60 * 60 * 1000L;

    // Generate Token

    public String generateToken(String userName, String role) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // get all claims which used to generate token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //  claim resolver
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    // extract UserName from the claim
    public String extractTokenUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extract UserRole from the claim
    public String extractTokenRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }

    // check token expiration
    private Date checkTokenExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpire(String token) {
        return checkTokenExpiration(token).before(new Date());
    }

    // validate token
    public boolean validateToken(String token, String userName) {
        String tokenUserName = extractTokenUserName(token);
        return (tokenUserName.equals(userName) && !isTokenExpire(token));
    }


}
