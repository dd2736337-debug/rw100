package com.vti.gold.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "fullname",nullable = false)
    private String fullName;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone",nullable = false,unique = true)
    private String phone;
    @Column(name = "address",nullable = false)
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;

}
