package com.example.demo.route.service;

import com.example.demo.attraction.dto.AttractionDto;

import java.util.List;

public interface RouteAttractionService {
    public List<AttractionDto> searchByTitle(String title);
    public List<AttractionDto> getLists(int routeId);
    public void createLists(int routeId, List<Integer> attractions);
    public void updateLists(int routeId, List<Integer> attractions);
    public AttractionDto insertItem(int routeId, int attId);
    public void deleteItem(int routeId, int attId);
    public void deleteLists(int routeId, List<Integer> attractions);
}
