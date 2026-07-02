package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "department") //mapping đến bảng department trong database
public class Department {
    @Id //đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Tự tăng
    @Column(name = "department_id")//truong này cho biết là thuộc tính  này map với cột department id trong DB
    private Integer id;
    @Column(name = "department_name", length = 100, nullable = false, unique = true)
    private String name;

    //Ko cần thêm vào,tại Db ko có cột này
//    @OneToOne(mappedBy = "de")
//    private Account account;

    //ko có cũng được
//    @OneToMany(mappedBy = "dep",fetch = FetchType.EAGER)
//    private List<Account> accounts;//list account thuộc  về department này


}
