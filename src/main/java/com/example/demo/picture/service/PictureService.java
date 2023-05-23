package com.example.demo.picture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PictureService {
    public PictureService() {
    }
}
