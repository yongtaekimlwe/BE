package com.example.demo.route.service;

import com.example.demo.route.repository.RouteTagRepoitory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RouteTagServiceImpl implements RouteTagService{

    private final RouteTagRepoitory routeTagRepoitory;

    private static final Logger logger = LoggerFactory.getLogger(RouteTagServiceImpl.class);

    @Override
    @Transactional
    public boolean addTags(int routeId, Map<String, Integer>[] tags) {
        for(int i = 0; i < tags.length; ++i) {
            logger.debug("!! {}, {}", routeId, tags[i].get("tagId"));
            routeTagRepoitory.addTags(routeId, tags[i].get("tagId"));
        }

        return true;
    }

    @Override
    @Transactional
    public boolean deleteTags(int routeId, Map<String, Integer>[] tags) {
        for(int i = 0; i < tags.length; ++i) {
            logger.debug("!! {}, {}", routeId, tags[i].get("tagId"));
            routeTagRepoitory.deleteTags(routeId, tags[i].get("tagId"));
        }
        return true;
    }
}
