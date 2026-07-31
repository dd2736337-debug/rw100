package com.vti.gold.controller;

import com.vti.gold.dto.AuthResponse;
import com.vti.gold.dto.LoginRequest;
import com.vti.gold.dto.RegisterRequest;
import com.vti.gold.entity.Role;
import com.vti.gold.entity.User;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    // ==========================
    // REGISTER
    // ==========================

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request
    ) {


        if (userRepository.existsByUsername(request.getUsername())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Username đã tồn tại!"
            );
        }


        if (request.getEmail() != null
                && userRepository.existsByEmail(request.getEmail())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email đã tồn tại!"
            );
        }


        if (userRepository.existsByPhone(request.getPhone())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Số điện thoại đã tồn tại!"
            );
        }


        User user = new User();


        user.setUsername(
                request.getUsername()
        );


        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );


        user.setFullName(
                request.getFullName()
        );


        user.setEmail(
                request.getEmail()
        );


        user.setPhone(
                request.getPhone()
        );


        user.setAddress(
                request.getAddress()
        );


        // Mặc định user đăng ký là CUSTOMER
        user.setRole(
                Role.CUSTOMER
        );


        userRepository.save(user);


        return ResponseEntity.ok(
                "Đăng ký thành công"
        );

    }


    // ==========================
    // LOGIN
    // ==========================

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request
    ) {


        User user =
                userRepository.findByUsername(
                                request.getUsername()
                        )
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.UNAUTHORIZED,
                                        "Sai username hoặc password!"
                                )
                        );


        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );


        if (!matches) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Sai username hoặc password!"
            );

        }


        String token =
                jwtTokenProvider.generateToken(
                        user.getUsername(),
                        user.getRole().name()
                );


        return new AuthResponse(
                user.getId(),
                token,
                user.getUsername(),
                user.getRole().name()
        );

    }

}