package com.example.demo.user.controller;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.OauthToken;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.service.JwtService;
import com.example.demo.user.service.OAuthService;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final OAuthService oAuthService;
    private final UserService userService;
    private final JwtService jwtService;

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

    // 회원 가입(일반 회원)
    @PostMapping("/join")
    public ResponseEntity<?> userRegist(@RequestBody UserDto userDto) {
        userService.join(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인(일반 회원) - 토큰 반환
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto) {
        Map<String, Object> res = new HashMap<>();
        HttpStatus status = null;

        logger.debug("이메일 정보: {}", userDto.getEmail());
        logger.debug("비밀번호: {}", userDto.getPassword());

        // 로그인 실패
        if(!userService.login(userDto)) return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);

        String token = jwtService.create("email", userDto.getEmail(), "access-token");
        logger.debug("로그인 토큰 정보: {}", token);
        res.put("access-token", token);
        res.put("message", "SUCCESS");
        status = HttpStatus.ACCEPTED;

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") String userId, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
				// 로그인 사용자 정보.
                UserDto memberDto = userService.userInfo(userId);
                resultMap.put("userInfo", memberDto);
                resultMap.put("message", "SUCCESS");
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", "FAIL");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        userService.updateInfo(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

