package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final IAccountRepository userRepository;

  public MyUserDetailsService(IAccountRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Account account = userRepository.findByEmail(email);
    if (account == null) {
      throw new UsernameNotFoundException(MessageConstant.ACCOUNT_NOT_EXISTS);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    account.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    });
    return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), authorities);
  }
}
