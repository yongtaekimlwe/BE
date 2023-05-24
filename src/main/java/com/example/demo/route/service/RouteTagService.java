package com.example.demo.route.service;

import java.util.Map;

public interface RouteTagService {

    boolean addTags(int routeId, Map<String, Integer>[] tags);

    boolean deleteTags(int routeId, Map<String, Integer>[] tags);
}
