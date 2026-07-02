package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    @Id //đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Tự tăng
    @Column(name = "account_id")//truong này cho biết là thuộc tính  này map với cột department id trong DB
    private Integer id;
    @Column(name = "username", length = 100, nullable = false,unique = true)
    private String userName;
    @Column(name = "full_name", length = 100, nullable = false,unique = true)
    private String fullName;
    @Column(name = "email", length = 100, nullable = false,unique = true)
    private String email;

    //username,.....

    //cấu hình khóa ngoại
    @ManyToOne
    @JoinColumn(name ="department_id")
    private Department dep;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;


    @CreationTimestamp
    @Column(name = "create_date",updatable = false)
    private LocalDateTime createDate;



}
