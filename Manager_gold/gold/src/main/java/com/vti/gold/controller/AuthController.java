package com.vti.gold.controller;

import com.vti.gold.dto.AuthResponse;
import com.vti.gold.dto.LoginRequest;
import com.vti.gold.dto.RegisterRequest;
import com.vti.gold.entity.Role;
import com.vti.gold.entity.User;
import com.vti.gold.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    // Đăng ký tài khoản
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username đã tồn tại!");
        }


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại!");
        }


        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Số điện thoại đã tồn tại!");
        }


        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setFullName(request.getFullName());


        user.setEmail(request.getEmail());


        user.setPhone(request.getPhone());


        user.setAddress(request.getAddress());


        // Người đăng ký mặc định là CUSTOMER
        user.setRole(Role.CUSTOMER);


        userRepository.save(user);


        return "Đăng ký thành công";

    }


    // Đăng nhập
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Sai username hoặc password!"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            throw new RuntimeException("Sai username hoặc password!");
        }


        // Tạm thời trả thông tin user
        // bước JWT sẽ thêm token ở đây

        return new AuthResponse("TOKEN_CHUA_TAO", user.getUsername(), user.getRole().name());

    }
}
