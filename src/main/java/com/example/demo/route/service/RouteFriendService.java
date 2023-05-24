package com.example.demo.route.service;

import com.example.demo.user.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface RouteFriendService {
    public List<UserDto> searchUser(String email);
    public boolean addFriends(int routeId, String friendEmail);
    public boolean deleteFriends(int routeId, String friendEmail);
}
