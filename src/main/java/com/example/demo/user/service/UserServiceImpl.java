package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.KakaoProfile;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    OAuthService oAuthService;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto saveUser(String token) {
        KakaoProfile profile = oAuthService.findProfile(token);

        User user = userRepository.findByEmail(profile.getKakao_account().getEmail());

        if(user == null) {
            user = new User("", profile.getKakao_account().getProfile().getNickname(), profile.getKakao_account().getEmail(), profile.getKakao_account().getProfile().getProfile_image_url());
            userRepository.save(user);
        }

        return new UserDto(user);
    }
}
