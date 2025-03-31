package com.examly.springapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings") // Table name in the database
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User table
    private User user;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false) // Foreign key to Hall table
    private Hall hall;

    private LocalDate bookingDate;

    // Default Constructor
    public Booking() {}

    // Parameterized Constructor
    public Booking(User user, Hall hall, LocalDate bookingDate) {
        this.user = user;
        this.hall = hall;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Hall getHall() { return hall; }
    public void setHall(Hall hall) { this.hall = hall; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
}
