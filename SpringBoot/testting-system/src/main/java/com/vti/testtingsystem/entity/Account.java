package com.vti.testtingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
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
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    @Column(name = "email", length = 100, nullable = false,unique = true)
    private String email;
    @CreationTimestamp
    @Column(name = "create_date",updatable = false)
    private LocalDateTime createDate;

    //username,.....

    //cấu hình khóa ngoại
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="department_id")
    @JsonIgnoreProperties("accounts")
    @ToString.Exclude
    private Department department;
}
