package com.vti.gold.controller;

import com.vti.gold.dto.UserDTO;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import com.vti.gold.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public Page<UserDTO> findAll(Pageable pageable){
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody UserCreateForm form){
        userService.create(form);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UserUpdateForm form,@PathVariable Integer id){
        userService.update(form,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userService.delete(id);
    }

}
