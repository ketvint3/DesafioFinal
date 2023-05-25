package com.Desafio.Final.Diamond.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtTokenService implements Serializable {

    private static final long serialVersionUID = -1652411099019301057L;
    public static final long TEMPO_VALIDADE_TOKEN = System.currentTimeMillis() * 60 * 24;
    private String secret = "5A7134743777217A25432A462D4A614E645267556B586E3272357538782F413F";

    private Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);

    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {

        String username;

        username = getUserNameFromToken(jwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));

    }

    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(TEMPO_VALIDADE_TOKEN))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }
}
