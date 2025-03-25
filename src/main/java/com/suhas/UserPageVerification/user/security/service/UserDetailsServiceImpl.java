package com.suhas.UserPageVerification.user.security.service;

import com.suhas.UserPageVerification.user.models.User;
import com.suhas.UserPageVerification.user.repository.UserRepository;
import com.suhas.UserPageVerification.user.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User name not found exception"+username));
        return UserDetailsImpl.build(user);
    }
}
