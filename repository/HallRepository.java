package com.examly.springapp.repository;

import com.examly.springapp.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {

    @Query("SELECT h FROM Hall h WHERE h.location = :location")
    List<Hall> findByLocation(@Param("location") String location);

    @Query("SELECT h FROM Hall h WHERE h.capacity >= :capacity")
    List<Hall> findByCapacityGreaterThanEqual(@Param("capacity") int capacity);

    @Query("SELECT h FROM Hall h WHERE h.name LIKE %:name%")
    List<Hall> findByNameContaining(@Param("name") String name);
}
