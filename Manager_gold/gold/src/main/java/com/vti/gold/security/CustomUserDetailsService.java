package com.vti.gold.security;


import com.vti.gold.entity.User;
import com.vti.gold.repository.UserRepository;


import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import java.util.Collections;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


    private final UserRepository userRepository;


    /**
     * Load user khi JWT filter cần xác thực
     * <p>
     * username lấy từ JWT subject
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user =

                userRepository.findByUsername(username)

                        .orElseThrow(() ->

                                new UsernameNotFoundException("Không tìm thấy tài khoản!")

                        );


        logger.info("Authentication user: {}", user.getUsername());


        return new org.springframework.security.core.userdetails.User(

                user.getUsername(),


                user.getPassword(),


                Collections.singletonList(

                        new SimpleGrantedAuthority(

                                "ROLE_" + user.getRole().name()

                        )

                )

        );


    }


}

