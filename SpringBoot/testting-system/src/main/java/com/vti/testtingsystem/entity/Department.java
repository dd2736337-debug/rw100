package com.vti.testtingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "department")
public class Department {
    //mapping đến bảng department trong database
    @Id //đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Tự tăng
    @Column(name = "department_id")//truong này cho biết là thuộc tính  này map với cột department id trong DB
    private Integer id;
    @Column(name = "department_name", length = 100, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Account> accounts;
}
