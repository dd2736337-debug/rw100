package com.vti.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`group`") //mapping đến bảng department trong database
public class Group {
    @Id //đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Tự tăng
    @Column(name = "group_id")//truong này cho biết là thuộc tính  này map với cột department id trong DB
    private Integer id;

    @Column(name = "group_name", length = 100, nullable = false, unique = true)
    private String groupName;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<GroupAccount> groupAccounts;





}
