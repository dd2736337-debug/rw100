package com.vti.repository;

import com.vti.entity.GroupAccount;

import java.util.List;

public interface IGroupAccount {
    List<GroupAccount> findAll();

    GroupAccount findById(Integer id);

    void create(GroupAccount groupAccount);

    void update(GroupAccount groupAccount);

    void  delete(Integer id);
}
