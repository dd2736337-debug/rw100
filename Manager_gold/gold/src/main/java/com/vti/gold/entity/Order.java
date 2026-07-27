package com.vti.gold.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oders")
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "totalprice")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OderStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
