package com.project.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.main.domain.entity.Role;
import com.project.main.domain.entity.User;
import com.project.main.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired  
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role role = new Role();
        role.setRoleid(1);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

}