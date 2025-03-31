package com.examly.springapp.service;

import com.examly.springapp.entity.Hall;
import com.examly.springapp.repository.HallRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    // Get All Halls with Pagination and Sorting
    public Page<Hall> getAllHalls(Pageable pageable, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return hallRepository.findAll(sortedPageable);
    }

    // Get Hall by ID
    public Optional<Hall> getHallById(Long id) {
        return hallRepository.findById(id);
    }

    // Create Hall
    public Hall saveHall(Hall hall) {
        return hallRepository.save(hall);
    }

    // Update Hall
    public Optional<Hall> updateHall(Long id, Hall hallDetails) {
        return hallRepository.findById(id).map(existingHall -> {
            existingHall.setName(hallDetails.getName());
            existingHall.setLocation(hallDetails.getLocation());
            existingHall.setCapacity(hallDetails.getCapacity());
            return hallRepository.save(existingHall);
        });
    }

    // Delete Hall
    public boolean deleteHall(Long id) {
        if (hallRepository.existsById(id)) {
            hallRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // JPQL Queries
    public List<Hall> getHallsByLocation(String location) {
        return hallRepository.findByLocation(location);
    }

    public List<Hall> getHallsByCapacity(int capacity) {
        return hallRepository.findByCapacityGreaterThanEqual(capacity);
    }

    public List<Hall> getHallsByName(String name) {
        return hallRepository.findByNameContaining(name);
    }
}
