package com.example.demo.user.service;

import com.example.demo.user.dto.KakaoProfile;
import com.example.demo.user.dto.OauthToken;

public interface OAuthService {
    public OauthToken getAccessToken(String code);
    public KakaoProfile findProfile(String token);
}
