package com.example.demo.route.controller;

import com.example.demo.route.dto.DetailRouteInfo;
import com.example.demo.route.service.RouteTagService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/route/tag")
@RequiredArgsConstructor
public class RouteTagController {

    private final RouteTagService routeTagService;

    private static final Logger logger = LoggerFactory.getLogger(RouteTagController.class);

    // 여행 경로 태그 추가
    @PostMapping("/add/{routeId}")
    public ResponseEntity<HttpStatus> addTags(@PathVariable int routeId, @RequestBody Map<String, Integer>[] tags) {
        logger.debug("addTags 진입!");
        routeTagService.addTags(routeId, tags);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 여행 경로 태그 삭제
    @DeleteMapping("/delete/{routeId}")
    public ResponseEntity<HttpStatus> deleteTags(@PathVariable int routeId, @RequestBody Map<String, Integer>[] tags) {
        logger.debug("deleteTags 진입!");
        routeTagService.deleteTags(routeId, tags);
        return new ResponseEntity(HttpStatus.OK);
    }
}
