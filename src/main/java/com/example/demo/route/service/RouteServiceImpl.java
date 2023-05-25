package com.example.demo.route.service;

import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.hashtag.service.HashtagService;
import com.example.demo.route.domain.Route;
import com.example.demo.route.dto.BriefRouteInfo;
import com.example.demo.route.dto.DetailRouteInfo;
import com.example.demo.route.dto.RouteDto;
import com.example.demo.route.repository.RouteRepository;
import com.example.demo.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService{

    private final RouteRepository routeRepository;
    private final HashtagService hashtagService;

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    // 제목, 작성자, 좋아요 수, 태그
    @Override
    public List<BriefRouteInfo> getAllRoutes() {
        List<RouteDto> list = routeRepository.findAll()
                .stream()
                .map(RouteDto::new)
                .collect(Collectors.toList());

        List<BriefRouteInfo> res = new ArrayList<>();
        for(RouteDto li : list) {
            BriefRouteInfo bri = new BriefRouteInfo();
            bri.setRouteId(li.getId());
            bri.setTitle(li.getTitle());
            bri.setUserName(li.getUser().getName());

            bri.setLikes(routeRepository.countLikes(li.getId()));
            logger.debug("좋아요 수 가져오기 {}",bri.getLikes());

            // HashTag 가져오기
            List<HashtagResponse> tags = new ArrayList<>();
            for (Map<String, String> tag : routeRepository.getHashtags(li.getId())) {
                HashtagResponse tmp = new HashtagResponse(Integer.parseInt(String.valueOf(tag.get("tagId"))), tag.get("tagName"), tag.get("tagIcon"));
                logger.debug("Hashtag 가져오기 {}",tmp);
                tags.add(tmp);
            }
            bri.setHashtags(tags);

            res.add(bri);
        }
        return res;
    }

    // id, 제목, 작성자, 좋아요 수, 태그
    // 내용, 같이 여행 가는 사람, 관광지 목록 추가 제공
    @Override
    public DetailRouteInfo getRoute(int routeId) {
        DetailRouteInfo route = new DetailRouteInfo();

        Route routeInfo = routeRepository.findById(routeId).orElse(new Route());
        route.setRouteId(routeId);
        route.setTitle(routeInfo.getTitle());
        route.setContent(routeInfo.getContent());
        route.setUserName(routeInfo.getUser().getName());
        route.setLikes(routeRepository.countLikes(routeId));
        route.setHashtags(routeRepository.getHashtags(routeId));
        // 같이 가는 친구들
        List<UserDto> parts = new ArrayList<>();
        for (Map<String, String> part : routeRepository.getParticipants(routeId)) {
            parts.add(new UserDto(part.get("user_name"), part.get("user_email"), part.get("profile_img_src")));
        }
        route.setParticipants(parts);
        // 여행지

        return route;
    }

    @Override
    public void deleteRoute(int routeId) {
        routeRepository.deleteById(routeId);
    }

    public int saveRoute(int userId, String title, String content){
        routeRepository.saveRoutes(userId, title, content);
        return routeRepository.getId();
    }

}
