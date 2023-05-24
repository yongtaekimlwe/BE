package com.example.demo.route.controller;

import com.example.demo.route.dto.BriefRouteInfo;
import com.example.demo.route.dto.DetailRouteInfo;
import com.example.demo.route.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

    private final RouteService routeService;

    // 모든 여행 경로 반환
    // 제목, 작성자, 좋아요 수, 태그
    @GetMapping
    public ResponseEntity<List<BriefRouteInfo>> getAllRoutes() {
        logger.debug("getAllRoutes 진입!");
        return ResponseEntity.ok(routeService.getAllRoutes());
    }
//
    // 특정 여행 경로에 관한 정보를 반환
    // 제목, 작성자, 좋아요수, 태그은 이미 가진 정보 활용
    // 같이 여행 가는 사람, 관광지 목록 추가 제공
    @GetMapping("/{routeId}")
    public ResponseEntity<DetailRouteInfo> getRoute(@PathVariable int routeId) {
        logger.debug("getRoute 진입!");
        DetailRouteInfo route = routeService.getRoute(routeId);
        return ResponseEntity.ok(route);
    }
//
//    // 여행 경로 생성
//    @PostMapping
//    public ResponseEntity<> createRoute(@RequestBody RouteDto routeDto) {
//        return null;
//    }
//
//    // 여행 경로 수정
//    @PutMapping
//    public ResponseEntity<> updateRoute(@RequestBody RouteDto routeDto) {
//        return null;
//    }
//
    // 여행 경로 삭제
    @DeleteMapping("/{routeId}")
    public void deleteRoute(@PathVariable int routeId) {
        routeService.deleteRoute(routeId);
    }
}
