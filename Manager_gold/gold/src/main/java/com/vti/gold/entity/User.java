package com.vti.gold.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Column(name = "email",nullable = false, unique = true)
    private String email;
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    @Column(name = "address", nullable = false)
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    public void beforeCreate() {

        createdAt = LocalDateTime.now();

        updatedAt = LocalDateTime.now();
        if (role == null) {
            role = Role.CUSTOMER;
        }

    }

    @PreUpdate
    public void beforeUpdate() {

        updatedAt = LocalDateTime.now();

    }

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Order> orders = new ArrayList<>();

}
