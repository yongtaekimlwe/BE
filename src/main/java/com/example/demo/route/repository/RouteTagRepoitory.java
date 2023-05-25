package com.example.demo.route.repository;

import com.example.demo.route.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RouteTagRepoitory extends JpaRepository<Route, Integer> {

    @Query(
            value = "INSERT INTO route_hashtag (route_id, tag_id) VALUES(:routeId, :tagId)",
            nativeQuery = true
    )
    @Modifying
    int addTags(@Param("routeId") int routeId, @Param("tagId") int tagId);

    @Query(
            value = "DELETE FROM route_hashtag WHERE route_id = :routeId and tag_id = :tagId",
            nativeQuery = true
    )
    @Modifying
    int deleteTags(@Param("routeId") int routeId, @Param("tagId") int tagId);
}
