package com.example.demo.route.service;

import com.example.demo.route.dto.BriefRouteInfo;
import com.example.demo.route.dto.DetailRouteInfo;

import java.util.List;

public interface RouteService {
    public List<BriefRouteInfo>getAllRoutes ();
    public DetailRouteInfo getRoute(int routeId);
//    public boolean createRoute(RouteDto route);
//    public boolean updateRoute(RouteDto route);
    public void deleteRoute(int routeId);
    int saveRoute(int userId, String title, String content);
}
