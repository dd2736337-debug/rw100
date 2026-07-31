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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public Page<UserDTO> findAll(@RequestParam(required = false) String username,

                                 @RequestParam(required = false) String email,

                                 @RequestParam(required = false) String fullName, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.findAll(username, email, fullName, pageable);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDTO create(@Valid @RequestBody UserCreateForm form) {

        return userService.create(form);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Integer id, @Valid @RequestBody UserUpdateForm form) {
        return userService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully with id: " + id);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @Valid @RequestBody ChangePasswordForm form) {

        userService.changePassword(id, form);
        return ResponseEntity.ok("Đổi mật khẩu thành công");

    }

}
