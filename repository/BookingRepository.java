package com.examly.springapp.repository;

import com.examly.springapp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Fetch all bookings for a specific user
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findBookingsByUserId(@Param("userId") Long userId);

    // Fetch all bookings for a specific hall
    @Query("SELECT b FROM Booking b WHERE b.hall.id = :hallId")
    List<Booking> findBookingsByHallId(@Param("hallId") Long hallId);

    // Fetch bookings within a date range
    @Query("SELECT b FROM Booking b WHERE b.bookingDate BETWEEN :startDate AND :endDate")
    List<Booking> findBookingsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
