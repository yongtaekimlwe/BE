package com.example.demo.user.service;

import com.example.demo.user.dto.UserDto;

public interface UserService {

    public UserDto saveUser(String token);
}
