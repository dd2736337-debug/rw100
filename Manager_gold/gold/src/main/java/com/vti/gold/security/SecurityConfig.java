package com.vti.gold.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

// =========================================================
// PASSWORD ENCODER
// =========================================================

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

// =========================================================
// SECURITY FILTER CHAIN
// =========================================================

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                // =================================================
                // CORS
                // =================================================

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // =================================================
                // CSRF
                // =================================================

                .csrf(csrf -> csrf.disable())

                // =================================================
                // SESSION
                // =================================================

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // =================================================
                // AUTHORIZATION
                // =================================================

                .authorizeHttpRequests(auth -> auth

                        // -------------------------------------------------
                        // CORS PREFLIGHT
                        // -------------------------------------------------

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // -------------------------------------------------
                        // AUTH
                        // -------------------------------------------------

                        .requestMatchers("/api/auth/**").permitAll()

                        // -------------------------------------------------
                        // UPLOADS
                        // -------------------------------------------------

                        .requestMatchers("/uploads/**").permitAll()

                        // =================================================
                        // GOLD
                        // =================================================

                        // CUSTOMER + ADMIN xem vàng
                        .requestMatchers(HttpMethod.GET, "/api/golds/**").permitAll()

                        // ADMIN thêm vàng
                        .requestMatchers(HttpMethod.POST, "/api/golds/**").hasRole("ADMIN")

                        // ADMIN sửa vàng
                        .requestMatchers(HttpMethod.PUT, "/api/golds/**").hasRole("ADMIN")

                        // ADMIN xóa vàng
                        .requestMatchers(HttpMethod.DELETE, "/api/golds/**").hasRole("ADMIN")

                        // =================================================
                        // CART
                        // =================================================

                        // CUSTOMER + ADMIN xem giỏ hàng
                        .requestMatchers(HttpMethod.GET, "/api/cart/**").hasAnyRole("ADMIN", "CUSTOMER")

                        // CUSTOMER + ADMIN thêm giỏ
                        .requestMatchers(HttpMethod.POST, "/api/cart/**").hasAnyRole("ADMIN", "CUSTOMER")

                        // CUSTOMER + ADMIN cập nhật giỏ
                        .requestMatchers(HttpMethod.PUT, "/api/cart/**").hasAnyRole("ADMIN", "CUSTOMER")

                        // CUSTOMER + ADMIN xoá giỏ
                        .requestMatchers(HttpMethod.DELETE, "/api/cart/**").hasAnyRole("ADMIN", "CUSTOMER")

                        // =================================================
                        // ORDER
                        // =================================================

                        // CUSTOMER + ADMIN tạo đơn
                        .requestMatchers(HttpMethod.POST, "/api/orders/**").hasAnyRole("ADMIN", "CUSTOMER")

                        // CUSTOMER + ADMIN xem đơn theo user
                        .requestMatchers(HttpMethod.GET, "/api/orders/user/**").hasAnyRole("ADMIN", "CUSTOMER")

                        // ADMIN xem tất cả đơn
                        .requestMatchers(HttpMethod.GET, "/api/orders/**").hasRole("ADMIN")

                        // ADMIN cập nhật đơn
                        .requestMatchers(HttpMethod.PUT, "/api/orders/**").hasRole("ADMIN")

                        // ADMIN xóa đơn
                        .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("ADMIN")

                        // =================================================
                        // USERS
                        // =================================================

                        // CUSTOMER + ADMIN xem thông tin user
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("ADMIN", "CUSTOMER")

                        // CUSTOMER + ADMIN đổi mật khẩu
                        //
                        // Quan trọng:
                        // endpoint này phải được khai báo TRƯỚC
                        // PUT /api/users/**
                        //
                        .requestMatchers(HttpMethod.PUT, "/api/users/*/change-password").hasAnyRole("ADMIN", "CUSTOMER")

                        // ADMIN cập nhật thông tin user
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")

                        // ADMIN tạo user
                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")

                        // ADMIN xóa user
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                        // =================================================
                        // CÁC API KHÁC
                        // =================================================

                        // =================================================
                        // USER MANAGEMENT
                        // =================================================

                        // ADMIN quản lý người dùng
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                        .anyRequest().authenticated())

                // =================================================
                // JWT FILTER
                // =================================================

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

// =========================================================
// CORS CONFIGURATION
// =========================================================

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:5500", "http://127.0.0.1:5500", "http://localhost:5501", "http://127.0.0.1:5501"));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

// =========================================================
// AUTHENTICATION MANAGER
// =========================================================

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


}
