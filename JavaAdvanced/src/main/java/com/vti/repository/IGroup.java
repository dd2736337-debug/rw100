package com.vti.repository;

import com.vti.entity.Group;

import java.util.List;

public interface IGroup {
    List<Group> findAll();

    Group findById(Integer Id);

    void create(Group group);

    void update(Group group);

    void delete(Integer Id);
}
