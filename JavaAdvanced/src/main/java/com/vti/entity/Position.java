package com.vti.entity;

import com.vti.enums.ArticlePositionNameConverter;
import com.vti.enums.PositionName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Integer id;


//    @Enumerated(EnumType.STRING)//String Ordinal
//    @Column(name = "position_name", length = 100, nullable = false, unique = true)
//    private PositionName name;

    @Convert(converter = ArticlePositionNameConverter.class)
    @Column(name = "position_name", length = 100, nullable = false, unique = true)
    private PositionName name;

    @OneToMany(mappedBy ="position",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Account> accounts;
}

