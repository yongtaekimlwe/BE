package com.example.demo.user.repository;

import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);

    @Query(
            value = "UPDATE user SET user_pw = :password, user_email = :email, user_name = :name WHERE user_id = :id",
            nativeQuery = true
    )
    @Modifying
    public void updateUser(@Param("password") String password, @Param("email") String email, @Param("name") String name, @Param("id") int id);
}
