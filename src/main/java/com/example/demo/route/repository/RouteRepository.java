package com.example.demo.route.repository;

import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.route.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query(
            value = "SELECT COUNT(*) FROM user_route_like WHERE route_id = :routeId",
            nativeQuery = true)
    int countLikes(@Param("routeId") int routeId);

    @Query(
            value = "SELECT h.tag_id as tagId, h.tag_name as tagName, h.tag_icon as tagIcon FROM hashtag h INNER JOIN route_hashtag rh ON h.tag_id = rh.tag_id WHERE rh.route_id = :routeId",
            nativeQuery = true)
    List<Map<String, String>> getHashtags(@Param("routeId") int routeId);

    @Query(
            value = "SELECT content FROM route WHERE route_id = :routeId",
            nativeQuery = true)
    String getContent(@Param("routeId") int routeId);

    @Query(
            value = "SELECT u.user_name, u.user_email, u.profile_img_src " +
                    "FROM User u INNER JOIN user_route_participate urp " +
                    "ON u.user_id = urp.user_id " +
                    "WHERE urp.route_id = :routeId",
            nativeQuery = true)
    List<Map<String, String>> getParticipants(@Param("routeId") int routeId);
}
