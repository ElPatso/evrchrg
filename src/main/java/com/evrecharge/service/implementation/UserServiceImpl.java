package com.evrecharge.service.implementation;

import com.evrecharge.entity.User;
import com.evrecharge.repository.UserRepository;
import com.evrecharge.service.UserService;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUsername(String email) {
        return userRepository.findUserByEmail(email);
    }
}
