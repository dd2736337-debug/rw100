package com.vti.testtingsystem.entity;


import com.vti.testtingsystem.Enum.PositionName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Integer id;


    @Enumerated(EnumType.STRING)//String Ordinal
    @Column(name = "position_name", length = 100, nullable = false, unique = true)
    private PositionName name;

//    @Convert(converter = ArticlePositionNameConverter.class)
//    @Column(name = "position_name", length = 100, nullable = false, unique = true)
//    private PositionName name;

//    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
//    @ToString.Exclude
//    private List<Account> accounts;
}

