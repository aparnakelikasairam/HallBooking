package com.examly.springapp.controller;

import com.examly.springapp.entity.Hall;
import com.examly.springapp.service.HallService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    // Get All Halls with Pagination and Sorting
    @GetMapping("get/halls")
    public Page<Hall> getAllHalls(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            Pageable pageable) {
        return hallService.getAllHalls(pageable, sortBy, sortOrder);
    }

    // Get Hall by ID
    @GetMapping("get/halls/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable Long id) {
        return hallService.getHallById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create Hall
    @PostMapping("post/halls")
    public Hall createHall(@RequestBody Hall hall) {
        return hallService.saveHall(hall);
    }

    // Update Hall
    @PutMapping("put/halls/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable Long id, @RequestBody Hall hallDetails) {
        return hallService.updateHall(id, hallDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Hall
    @DeleteMapping("delete/halls/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        if (hallService.deleteHall(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get Halls by Location using JPQL
    @GetMapping("get/halls/by-location")
    public List<Hall> getHallsByLocation(@RequestParam String location) {
        return hallService.getHallsByLocation(location);
    }

    // Get Halls by Capacity using JPQL
    @GetMapping("get/halls/by-capacity")
    public List<Hall> getHallsByCapacity(@RequestParam int capacity) {
        return hallService.getHallsByCapacity(capacity);
    }

    // Get Halls by Name using JPQL
    @GetMapping("get/halls/by-name")
    public List<Hall> getHallsByName(@RequestParam String name) {
        return hallService.getHallsByName(name);
    }
}
