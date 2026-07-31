package com.vti.gold.service;

import com.vti.gold.dto.UserDTO;
import com.vti.gold.form.ChangePasswordForm;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<UserDTO> findAll(
            String username,
            String email,
            String fullName,
            Pageable pageable);

    UserDTO findById(Integer id);

    UserDTO create(UserCreateForm form);

    UserDTO update( Integer id,UserUpdateForm form);

    void delete(Integer id);

    void changePassword(Integer id, ChangePasswordForm form);


}
