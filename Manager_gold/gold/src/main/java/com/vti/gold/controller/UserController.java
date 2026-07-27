package com.vti.gold.controller;

import com.vti.gold.dto.UserDTO;
import com.vti.gold.form.ChangePasswordForm;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import com.vti.gold.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public Page<UserDTO> findAll(@PageableDefault(
            page = 0,
            size = 5,
            sort = "id",
            direction = Sort.Direction.DESC)
                                 Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody UserCreateForm form) {
        userService.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody UserUpdateForm form) {
        userService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping("/{id}/change-password")
    public void changePassword(
            @PathVariable Integer id,
            @Valid @RequestBody ChangePasswordForm form) {

        userService.changePassword(id, form);
    }

}
