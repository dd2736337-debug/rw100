package com.vti.gold.service;

import com.vti.gold.dto.UserDTO;
import com.vti.gold.form.ChangePasswordForm;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<UserDTO> findAll(Pageable pageable);

    UserDTO findById(Integer id);

    void create(UserCreateForm form);

    void update( Integer id,UserUpdateForm form);

    void delete(Integer id);

    void changePassword(Integer id, ChangePasswordForm form);

}
