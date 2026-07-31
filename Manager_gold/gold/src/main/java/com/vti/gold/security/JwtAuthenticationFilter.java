package com.vti.gold.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private CustomUserDetailsService userDetailsService;


    // =====================================
    // BỎ QUA CÁC REQUEST KHÔNG CẦN JWT
    // =====================================


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {


        String path = request.getServletPath();


        return path.startsWith("/api/auth/") || path.startsWith("/uploads/");


    }


    // =====================================
    // JWT FILTER
    // =====================================


    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain

    ) throws ServletException, IOException {


        String authorizationHeader = request.getHeader("Authorization");


        String username = null;

        String token = null;


        // ===============================
        // LẤY TOKEN
        // ===============================


        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {


            token = authorizationHeader.substring(7);


            // ===============================
            // VALIDATE TOKEN
            // ===============================


            if (jwtTokenProvider.validateToken(token)) {


                username = jwtTokenProvider.getUsernameFromToken(token);


            }


        }


        // ===============================
        // TẠO AUTHENTICATION
        // ===============================


        if (

                username != null

                        &&

                        SecurityContextHolder.getContext().getAuthentication() == null

        ) {


            try {


                UserDetails userDetails =

                        userDetailsService.loadUserByUsername(username);


                UsernamePasswordAuthenticationToken authentication =


                        new UsernamePasswordAuthenticationToken(

                                userDetails,

                                null,

                                userDetails.getAuthorities()

                        );


                authentication.setDetails(

                        new WebAuthenticationDetailsSource()

                                .buildDetails(request)

                );


                SecurityContextHolder

                        .getContext()

                        .setAuthentication(authentication);


            } catch (Exception e) {


                SecurityContextHolder

                        .clearContext();


            }


        }


        // Cho request tiếp tục

        filterChain.doFilter(

                request,

                response

        );


    }


}

