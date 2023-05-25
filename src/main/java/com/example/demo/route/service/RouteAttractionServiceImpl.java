package com.example.demo.route.service;

import com.example.demo.attraction.dto.AttractionDto;
import com.example.demo.route.repository.RouteAttractionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RouteAttractionServiceImpl implements RouteAttractionService{

    private final RouteAttractionRepository routeAttractionRepository;

    private final Logger logger = LoggerFactory.getLogger(RouteAttractionServiceImpl.class);

    @Override
    public List<AttractionDto> searchByTitle(String title) {
        List<Map<String, String>> list = routeAttractionRepository.searchByTitle(title);

        List<AttractionDto> res = new ArrayList<>();
        for(Map<String, String> li : list) {
            int id = Integer.parseInt(String.valueOf(li.get("content_id")));
            String ttitle = li.get("title");
            String addr = li.get("addr1");
            float latitude = Float.parseFloat(String.valueOf(li.get("latitude")));
            float longitude = Float.parseFloat(String.valueOf(li.get("longitude")));
            res.add(new AttractionDto(id, ttitle, addr, latitude, longitude));
        }

        return res;
    }

    @Override
    public List<AttractionDto> getLists(int routeId) {
        List<Map<String, String>> list = routeAttractionRepository.getLists(routeId);

        List<AttractionDto> res = new ArrayList<>();
        for(Map<String, String> li : list) {
            int id = Integer.parseInt(String.valueOf(li.get("content_id")));
            String ttitle = li.get("title");
            String addr = li.get("addr1");
            float latitude = Float.parseFloat(String.valueOf(li.get("latitude")));
            float longitude = Float.parseFloat(String.valueOf(li.get("longitude")));
            res.add(new AttractionDto(id, ttitle, addr, latitude, longitude));
        }

        return res;
    }

    @Override
    public void createLists(int routeId, List<Integer> attractions) {
        for(int att : attractions) {
            routeAttractionRepository.create(routeId, att);
        }
    }

    @Override
    public void updateLists(int routeId, List<Integer> attractions) {
        for(int att : attractions) {
            routeAttractionRepository.delete(routeId, att);
        }

        for(int att : attractions) {
            routeAttractionRepository.create(routeId, att);
        };
    }

    @Override
    public AttractionDto insertItem(int routeId, int attId) {
        routeAttractionRepository.create(routeId, attId);
        Map<String, String> map = routeAttractionRepository.get(attId);

        int id = Integer.parseInt(String.valueOf(map.get("content_id")));
        String ttitle = map.get("title");
        String addr = map.get("addr1");
        float latitude = Float.parseFloat(String.valueOf(map.get("latitude")));
        float longitude = Float.parseFloat(String.valueOf(map.get("longitude")));
        return new AttractionDto(id, ttitle, addr, latitude, longitude);
    }

    @Override
    public void deleteItem(int routeId, int attId) {
        routeAttractionRepository.delete(routeId, attId);
    }

    @Override
    public void deleteLists(int routeId, List<Integer> attractions) {
        for(int att : attractions) {
            routeAttractionRepository.delete(routeId, att);
        }
    }
}
