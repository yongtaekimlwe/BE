package com.example.demo.user.controller;

import com.example.demo.user.dto.OauthToken;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.JwtService;
import com.example.demo.user.service.OAuthService;
import com.example.demo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 로그인(카카오 소셜 로그인)
    @GetMapping("/kakao/token")
    public ResponseEntity<UserDto> login(@PathParam("code") String code) {
        logger.debug("/user/kakao/token 진입!");

        // 넘어온 인가 코드를 통해 access token 발급
        OauthToken oauthToken = oAuthService.getAccessToken(code);


        // 발급 받은 accessToken으로 카카오 회원 정보 DB 저장
        UserDto user = userService.saveUser(oauthToken.getAccess_token());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    // 로그인(일반 회원)
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto) {
//        Map<String, Object> user = new HashMap<>();
//        HttpStatus status = null;
//
//        try {
//            UserDto loginUser = userSer
//        }
//    }

}
