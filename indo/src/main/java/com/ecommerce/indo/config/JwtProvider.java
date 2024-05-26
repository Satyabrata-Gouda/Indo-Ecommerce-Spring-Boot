package com.ecommerce.indo.config;

import com.ecommerce.indo.constant.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    SecretKey key = Keys.hmacShaKeyFor(Jwt.SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication){

        String token = Jwts.builder()
                .setIssuer("")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getDate()+846000000))
                .claim("email",authentication.getName())
                .signWith(key)
                .compact();

        return token;
    }

    public String emailFromJwtExtractor(String jwt){

        jwt = jwt.substring(7);

        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));

    }

}
