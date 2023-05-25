package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserDto;

public interface UserService {

    public UserDto saveUser(String token);

    public void join(UserDto dto);

    public boolean login(UserDto userDto);

    public UserDto userInfo(String email);

    public void updateInfo(UserDto userDto);

    public void deleteUser(int userId);
}
