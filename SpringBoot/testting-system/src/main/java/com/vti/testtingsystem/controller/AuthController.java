package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.form.LoginForm;
import com.vti.testtingsystem.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    //đăng nhập ,đăng kí,quên mk
    @Autowired
    private IAccountService accountService;

//    @GetMapping("/login")
//    public ResponseEntity<?> login(Principal principal){
//        return new ResponseEntity<>(accountService.login(principal),HttpStatus.OK);

    @GetMapping("/login")
    public  ResponseEntity<?>login(@RequestBody LoginForm loginForm){
        return new ResponseEntity<>(accountService.login(loginForm),HttpStatus.OK);
    }

}
