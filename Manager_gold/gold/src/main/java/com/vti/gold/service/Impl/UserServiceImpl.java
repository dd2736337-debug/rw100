package com.vti.gold.service.Impl;


import com.vti.gold.Specification.UserSpecification;
import com.vti.gold.dto.UserDTO;
import com.vti.gold.entity.Role;
import com.vti.gold.entity.User;
import com.vti.gold.form.ChangePasswordForm;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import com.vti.gold.repository.OrderRepository;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<UserDTO> findAll(String username, String email, String fullName, Pageable pageable) {
        Specification<User> specification = Specification.allOf(UserSpecification.hasUsername(username), UserSpecification.hasEmail(email), UserSpecification.hasFullName(fullName));
        Page<User> page = userRepository.findAll(specification, pageable);
        return page.map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO create(UserCreateForm form) {
        if (userRepository.existsByUsername(form.getUsername())) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        if (userRepository.existsByEmail(form.getEmail())) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        if (userRepository.existsByPhone(form.getPhone())) {
            throw new RuntimeException("Số điện thoại đã tồn tại!");
        }
        User user = modelMapper.map(form, User.class);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        if (form.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        } else {
            user.setRole(form.getRole());
        }
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO update(Integer id, UserUpdateForm form) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));

        Optional<User> email = userRepository.findByEmail(form.getEmail());

        if (email.isPresent() && !email.get().getId().equals(id)) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        Optional<User> phone = userRepository.findByPhone(form.getPhone());

        if (phone.isPresent() && !phone.get().getId().equals(id)) {

            throw new RuntimeException("Số điện thoại đã tồn tại!");
        }

        user.setFullName(form.getFullName());
        user.setPhone(form.getPhone());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());

        if (form.getRole() != null) {
            user.setRole(form.getRole());
        }

        User saved = userRepository.save(user);

        return modelMapper.map(saved, UserDTO.class);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found!");
        }

        if (orderRepository.existsByUser_Id(id)) {
            throw new RuntimeException("Không thể xóa user đã có đơn hàng!");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changePassword(Integer id, ChangePasswordForm form) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        if (!passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không đúng!");
        }

        // không cho mật khẩu mới giống cũ

        if (passwordEncoder.matches(form.getNewPassword(), user.getPassword())) {


            throw new RuntimeException("Mật khẩu mới không được giống mật khẩu cũ!");

        }

        user.setPassword(passwordEncoder.encode(form.getNewPassword()));

        userRepository.save(user);
    }


}
