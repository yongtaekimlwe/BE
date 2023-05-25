package com.example.demo.user.service;

import com.example.demo.user.dto.UserDto;

public interface UserService {

    public UserDto saveUser(String token);

    public void join(UserDto dto);

    public boolean login(UserDto userDto);

    public UserDto userInfo(String email);
}
