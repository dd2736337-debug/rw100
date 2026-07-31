package com.vti.gold.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "golds")
public class Gold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 100)
    private String name;


    @Column(nullable = false, length = 50)
    private String type;


    @Column(precision = 10, scale = 2)
    private BigDecimal weight;


    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;


    @Column(nullable = false)
    private Integer quantity =0;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties("golds")
    private Category category;

    @Column(length = 255)
    private String image;
}