package com.vti.gold.service;

import com.vti.gold.dto.UserDTO;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<UserDTO> findAll(Pageable pageable);

    UserDTO findById(Integer id);

    void create(UserCreateForm form);

    void update(UserUpdateForm form, Integer id);

    void delete(Integer id);

}
