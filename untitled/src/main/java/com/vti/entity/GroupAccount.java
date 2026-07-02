package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccount {
    @Id //đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Tự tăng
    @Column(name = "id")//truong này cho biết là thuộc tính  này map với cột department id trong DB
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name ="group_id")
    private Group group;


    @CreationTimestamp
    @Column(name = "join_date",updatable = false)
    private LocalDateTime joinDate;
}
