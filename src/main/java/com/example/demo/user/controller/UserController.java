package com.example.demo.user.controller;

import com.example.demo.user.dto.OauthToken;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.OAuthService;
import com.example.demo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/kakao/token")
    public UserDto getLogin(@RequestParam("code") String code) {
        // 넘어온 인가 코드를 통해 access token 발급
        OauthToken oauthToken = oAuthService.getAccessToken(code);

        // 발급 받은 accessToken으로 카카오 회원 정보 DB 저장
        UserDto user = userService.saveUser(oauthToken.getAccess_token());

        return user;
    }
}
