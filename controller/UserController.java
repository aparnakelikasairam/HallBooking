package com.examly.springapp.controller;

import com.examly.springapp.entity.User;
import com.examly.springapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User (POST)
    @PostMapping("post/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new user", description = "Adds a new user to the system")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Get All Users with Pagination and Sorting (GET)
    @GetMapping("get/users")
    @Operation(summary = "Get all users with pagination and sorting", description = "Fetches paginated users list with sorting options")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return userService.getAllUsers(page, size, sortField, sortDir);
    }

    // Get User by ID (GET)
    @GetMapping("get/users/{id}")
    @Operation(summary = "Get user by ID", description = "Fetches a user by their unique ID")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update User (PUT)
    @PutMapping("put/users/{id}")
    @Operation(summary = "Update user details", description = "Updates user details based on ID")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete User (DELETE)
    @DeleteMapping("delete/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a user", description = "Deletes a user by their unique ID")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // JPQL-based APIs

    // Find users by name
    @GetMapping("get/users/by-name")
    @Operation(summary = "Find users by name", description = "Search users by name using JPQL")
    public List<User> getUsersByName(@RequestParam String name) {
        return userService.findUsersByName(name);
    }

    // Find user by email
    @GetMapping("get/users/by-email")
    @Operation(summary = "Find user by email", description = "Find user by exact email using JPQL")
    public User getUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    // Find users by phone prefix
    @GetMapping("get/users/by-phone-prefix")
    @Operation(summary = "Find users by phone prefix", description = "Find users whose phone starts with a given prefix")
    public List<User> getUsersByPhonePrefix(@RequestParam String prefix) {
        return userService.findUsersByPhonePrefix(prefix);
    }
}
