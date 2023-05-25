package com.example.demo.route.repository;

import com.example.demo.route.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RouteFriendRepository extends JpaRepository<Route, Integer> {

    @Query(
            value = "SELECT user_name as name, user_email as email, profile_img_src as img " +
                    "FROM USER " +
                    "WHERE user_email like %:email%",
            nativeQuery = true)
    List<Map<String, String>> searchUserByEmail(@Param("email") String email);

    @Query(
            value = "INSERT INTO user_route_participate (route_id, user_id) VALUES (:routeId, :userId)",
            nativeQuery = true
    )
    @Modifying
    int addFriend(@Param("routeId") int routeId, @Param("userId") int userId);

    @Query(
            value = "DELETE FROM user_route_participate WHERE route_id = :routeId and user_id = :userId",
            nativeQuery = true
    )
    @Modifying
    int deleteFriend(@Param("routeId") int routeId, @Param("userId") int userId);
}
