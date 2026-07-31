
package com.vti.gold.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtTokenProvider {


    private static final Logger logger =
            LoggerFactory.getLogger(JwtTokenProvider.class);


    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.expiration}")
    private long expiration;


    // ==================================
    // CREATE SECRET KEY
    // ==================================


    private SecretKey getSigningKey() {


        return Keys.hmacShaKeyFor(

                secret.getBytes(
                        StandardCharsets.UTF_8
                )

        );


    }


    // ==================================
    // GENERATE TOKEN
    // ==================================


    public String generateToken(

            String username,

            String role

    ) {


        Date now =
                new Date();


        Date expiryDate =

                new Date(
                        now.getTime()
                                +
                                expiration
                );


        return Jwts.builder()


                // username lưu trong subject

                .setSubject(username)


                // lưu quyền

                .claim(
                        "role",
                        role
                )


                // thời gian tạo

                .setIssuedAt(now)


                // thời gian hết hạn

                .setExpiration(expiryDate)


                // ký token

                .signWith(

                        getSigningKey(),

                        SignatureAlgorithm.HS256

                )


                .compact();


    }


    // ==================================
    // PARSE CLAIMS
    // ==================================


    private Claims getClaims(
            String token
    ) {


        return Jwts.parserBuilder()


                .setSigningKey(
                        getSigningKey()
                )


                .build()


                .parseClaimsJws(token)


                .getBody();


    }


    // ==================================
    // GET USERNAME
    // ==================================


    public String getUsernameFromToken(

            String token

    ) {


        return getClaims(token)
                .getSubject();


    }


    // ==================================
    // GET ROLE
    // ==================================


    public String getRoleFromToken(

            String token

    ) {


        return getClaims(token)

                .get(
                        "role",
                        String.class
                );


    }


    // ==================================
    // VALIDATE TOKEN
    // ==================================


    public boolean validateToken(

            String token

    ) {


        try {


            getClaims(token);


            return true;


        } catch (ExpiredJwtException e) {


            logger.warn(
                    "JWT đã hết hạn"
            );


        } catch (MalformedJwtException e) {


            logger.warn(
                    "JWT sai định dạng"
            );


        } catch (SignatureException e) {


            logger.warn(
                    "JWT sai chữ ký"
            );


        } catch (Exception e) {


            logger.error(
                    "JWT lỗi: {}",
                    e.getMessage()
            );


        }


        return false;


    }


}

