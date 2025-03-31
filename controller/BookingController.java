package com.examly.springapp.controller;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Get All Bookings with Pagination and Sorting
    @GetMapping
    public Page<Booking> getAllBookings(Pageable pageable) {
        return bookingService.getAllBookings(pageable);
    }

    // Get Booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a Booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.saveBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    // Update a Booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        return bookingService.updateBooking(id, bookingDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a Booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingService.deleteBooking(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ** Custom JPQL Queries **

    // Get Bookings by User ID
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    // Get Bookings by Hall ID
    @GetMapping("/hall/{hallId}")
    public List<Booking> getBookingsByHall(@PathVariable Long hallId) {
        return bookingService.getBookingsByHall(hallId);
    }

    // Get Bookings within a Date Range
    @GetMapping("/date-range")
    public List<Booking> getBookingsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return bookingService.getBookingsByDateRange(startDate, endDate);
    }
}
