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


    // ================================
    // PASSWORD ENCODER
    // ================================


    @Bean
    public PasswordEncoder passwordEncoder() {


        return new BCryptPasswordEncoder();


    }


    // ================================
    // SECURITY CONFIG
    // ================================


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http


                // CORS

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))


                // REST API disable CSRF

                .csrf(csrf -> csrf.disable())


                // JWT Stateless

                .sessionManagement(session ->

                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                )


                // AUTHORIZATION

                .authorizeHttpRequests(auth -> auth


                        // OPTIONS CORS

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()


                        // LOGIN + REGISTER

                        .requestMatchers("/api/auth/**").permitAll()


                        // IMAGE

                        .requestMatchers("/uploads/**").permitAll()


                        // =====================
                        // GOLD
                        // =====================


                        // CUSTOMER + ADMIN xem vàng

                        .requestMatchers(HttpMethod.GET, "/api/golds/**").hasAnyRole("ADMIN", "CUSTOMER")


                        // ADMIN thêm vàng

                        .requestMatchers(HttpMethod.POST, "/api/golds/**").hasRole("ADMIN")


                        // ADMIN sửa vàng

                        .requestMatchers(HttpMethod.PUT, "/api/golds/**").hasRole("ADMIN")


                        // ADMIN xóa vàng

                        .requestMatchers(HttpMethod.DELETE, "/api/golds/**").hasRole("ADMIN")


                        // =====================
                        // ORDER
                        // =====================


                        // CUSTOMER tạo đơn

                        .requestMatchers(HttpMethod.POST, "/api/orders/**").hasAnyRole("ADMIN", "CUSTOMER")


                        // CUSTOMER xem đơn của mình

                        .requestMatchers(HttpMethod.GET, "/api/orders/my/**").hasRole("CUSTOMER")


                        // ADMIN xem tất cả đơn

                        .requestMatchers(HttpMethod.GET, "/api/orders/**").hasRole("ADMIN")


                        // ADMIN cập nhật trạng thái

                        .requestMatchers(HttpMethod.PUT, "/api/orders/**").hasRole("ADMIN")


                        // ADMIN xóa đơn

                        .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("ADMIN")


                        // Các API còn lại bắt buộc login

                        .anyRequest().authenticated()


                )


                // JWT FILTER


                .addFilterBefore(

                        jwtAuthenticationFilter,

                        UsernamePasswordAuthenticationFilter.class

                );


        return http.build();


    }


    // ================================
    // CORS CONFIG
    // ================================


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {


        CorsConfiguration configuration = new CorsConfiguration();


        configuration.setAllowedOrigins(

                List.of(

                        "http://localhost:5500",

                        "http://127.0.0.1:5500",

                        "http://localhost:5501",

                        "http://127.0.0.1:5501"

                )

        );


        configuration.setAllowedMethods(

                List.of(

                        "GET",

                        "POST",

                        "PUT",

                        "DELETE",

                        "OPTIONS"

                )

        );


        configuration.setAllowedHeaders(

                List.of("*")

        );


        configuration.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source =

                new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration(

                "/**",

                configuration

        );


        return source;


    }


    // ================================
    // AUTHENTICATION MANAGER
    // ================================


    @Bean
    public AuthenticationManager authenticationManager(

            AuthenticationConfiguration configuration

    ) throws Exception {


        return configuration.getAuthenticationManager();


    }


}

