package com.vti.gold.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.expiration}")
    private long expiration;

    // Tạo token

    public String generateToken(String username) {


        Date now = new Date();


        Date expiryDate = new Date(now.getTime() + expiration);


        return Jwts.builder()

                .setSubject(username)

                .setIssuedAt(now)

                .setExpiration(expiryDate)

                .signWith(SignatureAlgorithm.HS256, secret)

                .compact();

    }

    // Lấy username từ token

    public String getUsernameFromToken(String token) {


        Claims claims = Jwts.parser()

                .setSigningKey(secret)

                .parseClaimsJws(token)

                .getBody();


        return claims.getSubject();

    }

    // Kiểm tra token hợp lệ

    public boolean validateToken(String token) {

        try {


            Jwts.parser()

                    .setSigningKey(secret)

                    .parseClaimsJws(token);


            return true;


        } catch (Exception e) {


            return false;

        }

    }

}


}
