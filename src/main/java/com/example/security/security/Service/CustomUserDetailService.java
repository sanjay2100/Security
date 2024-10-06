package com.example.security.security.Service;

import com.example.security.security.Modals.User;
import com.example.security.security.Principles.UserPrinciples;
import com.example.security.security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrinciples(user);
    }
}
