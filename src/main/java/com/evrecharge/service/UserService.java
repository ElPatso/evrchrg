package com.evrecharge.service;

import com.evrecharge.entity.User;

public interface UserService {

    User findByUsername(String username);

    User getCurrentUser();

}
