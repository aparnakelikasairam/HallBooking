package com.examly.springapp.service;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Get All Bookings with Pagination
    public Page<Booking> getAllBookings(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    // Get Booking by ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Create a Booking
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Update a Booking
    public Optional<Booking> updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(existingBooking -> {
            existingBooking.setUser(bookingDetails.getUser());
            existingBooking.setHall(bookingDetails.getHall());
            existingBooking.setBookingDate(bookingDetails.getBookingDate());
            return bookingRepository.save(existingBooking);
        });
    }

    // Delete a Booking
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ** JPQL Queries **

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findBookingsByUserId(userId);
    }

    public List<Booking> getBookingsByHall(Long hallId) {
        return bookingRepository.findBookingsByHallId(hallId);
    }

    public List<Booking> getBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findBookingsByDateRange(startDate, endDate);
    }
}
