package com.examly.springapp.repository;

import com.examly.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by name (case-insensitive search)
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByName(@Param("name") String name);

    // Find user by email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    // Find users whose phone starts with a specific prefix
    @Query("SELECT u FROM User u WHERE u.phone LIKE CONCAT(:prefix, '%')")
    List<User> findByPhonePrefix(@Param("prefix") String prefix);
}
