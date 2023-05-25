package com.example.demo.route.service;

import com.example.demo.route.dto.BriefRouteInfo;
import com.example.demo.route.dto.DetailRouteInfo;
import com.example.demo.route.dto.RouteDto;

import java.util.List;
import java.util.Map;

public interface RouteService {
    public List<BriefRouteInfo>getAllRoutes ();
    public DetailRouteInfo getRoute(int routeId);
//    public boolean createRoute(RouteDto route);
//    public boolean updateRoute(RouteDto route);
    public void deleteRoute(int routeId);
}
