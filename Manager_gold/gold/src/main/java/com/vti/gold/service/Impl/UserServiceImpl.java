package com.vti.gold.service.Impl;


import com.vti.gold.dto.UserDTO;
import com.vti.gold.entity.Role;
import com.vti.gold.entity.User;
import com.vti.gold.form.ChangePasswordForm;
import com.vti.gold.form.UserCreateForm;
import com.vti.gold.form.UserUpdateForm;
import com.vti.gold.repository.UserRepository;
import com.vti.gold.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void create(UserCreateForm form) {
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
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
    }

    @Override
    public void update(Integer id, UserUpdateForm form) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setFullName(form.getFullName());
        user.setPhone(form.getPhone());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(Integer id, ChangePasswordForm form) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        if (!user.getPassword()
                .equals(form.getOldPassword())) {

            throw new RuntimeException(
                    "Mật khẩu cũ không đúng!"
            );
        }

        user.setPassword(
                form.getNewPassword()
        );

        userRepository.save(user);
    }


}
