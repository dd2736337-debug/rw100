package com.vti.testtingsystem.config;

import com.vti.testtingsystem.entity.Account;
import com.vti.testtingsystem.exception.BusinessException;
import com.vti.testtingsystem.repository.IAccountRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service

public class UserDetaiSeviceImpl implements UserDetailsService {
    private IAccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account=accountRepository.findByUserName(username);
        if (Objects.isNull(account)){
            throw BusinessException.builder().message("Username not found").build();
        }
        return new User(username,account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().name()));
    }
}
