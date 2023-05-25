package com.example.demo.route.service;

import com.example.demo.route.repository.RouteFriendRepository;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RouteFriendServiceImpl implements RouteFriendService{

    private final RouteFriendRepository routeFriendRepository;
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Override
    public List<UserDto> searchUser(String email) {
        List<Map<String, String>> list = routeFriendRepository.searchUserByEmail(email);

        List<UserDto> res = new ArrayList<>();
        for(Map<String, String> li : list) {
            UserDto tmp = new UserDto(Integer.parseInt(String.valueOf(li.get("id"))), "", li.get("name"), li.get("email"), li.get("img"));
            res.add(tmp);
        }
        return res;
    }

    @Override
    @Transactional
    public boolean addFriends(int routeId, String friendEmail) {
        int userId = userRepository.findByEmail(friendEmail).getId();
        routeFriendRepository.addFriend(routeId, userId);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteFriends(int routeId, String friendEmail) {
        int userId = userRepository.findByEmail(friendEmail).getId();
        logger.debug("userId :: {}", userId);
        routeFriendRepository.deleteFriend(routeId, userId);
        return true;
    }


}
