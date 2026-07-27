package com.vti.gold.service.Impl;


import com.vti.gold.dto.UserDTO;
import com.vti.gold.entity.User;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> page=userRepository.findAll(pageable);
        return page.map(user -> modelMapper.map(user,UserDTO.class));
    }

    @Override
    public UserDTO findById(Integer id) {
        User user =userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found!"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void create(UserCreateForm form) {
        User user=modelMapper.map(form,User.class);
        userRepository.save(user);
    }

    @Override
    public void update(UserUpdateForm form, Integer id) {
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found!"));
        user.setFullName(form.getFullName());
        user.setPhone(form.getPhone());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setRole(form.getRole());
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
