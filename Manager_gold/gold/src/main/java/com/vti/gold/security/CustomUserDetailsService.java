package com.vti.gold.security;

import com.vti.gold.entity.User;
import com.vti.gold.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user!"));
        return new org.springframework.security.core.userdetails.User(

                user.getUsername(),

                user.getPassword(),


                Collections.singleton(

                        new SimpleGrantedAuthority("ROLE_" + user.getRole().name())

                )

        );
    }
}
