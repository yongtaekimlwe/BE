package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.KakaoProfile;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final OAuthService oAuthService;
    private final UserRepository userRepository;

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

    @Override
    public void join(UserDto dto) {
        userRepository.save(new User(dto.getPassword(), dto.getName(), dto.getEmail(), dto.getImgSrc()));
    }

    @Override
    public boolean login(UserDto userDto) {
        if(userDto.getEmail() == null || userDto.getPassword() == null) return false;
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user.getEmail().equals(userDto.getEmail()) && user.getPassword().equals(userDto.getPassword())) return true;
        else return false;
    }

    @Override
    public UserDto userInfo(String email) {
        User user = userRepository.findByEmail(email);
        return new UserDto(user.getId(), user.getPassword(), user.getName(), user.getEmail(), user.getImgSrc());
    }

    @Override
    @Transactional
    public void updateInfo(UserDto userDto) {
        userRepository.updateUser(userDto.getPassword(), userDto.getEmail(), userDto.getName(), userDto.getId());
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
