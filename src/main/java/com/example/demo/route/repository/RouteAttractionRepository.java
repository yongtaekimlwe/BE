package com.example.demo.route.repository;

import com.example.demo.route.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RouteAttractionRepository extends JpaRepository<Route, Integer> {

    @Query(
            value = "SELECT * FROM attraction_info WHERE title like %:title%",
            nativeQuery = true)
    List<Map<String, String>> searchByTitle(String title);

    @Query(
            value = "SELECT * FROM attraction_info ai JOIN route_has_attraction rha " +
                    "ON ai.content_id = rha.content_id " +
                    "WHERE rha.route_id = :routeId " +
                    "ORDER BY `order`",
            nativeQuery = true)
    List<Map<String, String>> getLists(int routeId);

    @Query(
            value = "INSERT INTO route_has_attracton(route_id, content_id) " +
                    "VALUES(:routeId, :contentId) ",
            nativeQuery = true)
    void create(int routeId, int contentId);

    @Query(
            value = "SELECT * " +
                    "FROM attractin_info ",
            nativeQuery = true)
    Map<String, String> get(int contentId);

    @Query(
            value = "DELETE FROM route_has_attracton " +
                    "WHERE route_id = :routeId AND contentId = :contentId ",
            nativeQuery = true)
    void delete(int routeId, int contentId);
}
