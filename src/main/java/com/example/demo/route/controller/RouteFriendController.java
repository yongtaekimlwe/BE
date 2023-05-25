package com.example.demo.route.controller;


import com.example.demo.route.service.RouteFriendService;
import com.example.demo.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route/friend")
@RequiredArgsConstructor
public class RouteFriendController {

    private final RouteFriendService routeFriendService;

    @GetMapping
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam String email) {
        return ResponseEntity.ok(routeFriendService.searchUser(email));
    }

    @GetMapping("/add/{routeId}")
    public ResponseEntity<?> addFriend(@PathVariable int routeId, @RequestParam String email) {
        routeFriendService.addFriends(routeId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/{routeId}")
    public ResponseEntity<?> deleteFriend(@PathVariable int routeId, @RequestParam String email) {
        routeFriendService.deleteFriends(routeId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
