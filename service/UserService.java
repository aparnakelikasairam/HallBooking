package com.examly.springapp.service;

import com.examly.springapp.entity.User;
import com.examly.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or Update User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get All Users with Pagination and Sorting
    public Page<User> getAllUsers(int page, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update User by ID
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            return userRepository.save(user);
        }).orElse(null);
    }

    // Delete User by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // JPQL Methods

    // Find users by name
    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }

    // Find user by email
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Find users by phone prefix
    public List<User> findUsersByPhonePrefix(String prefix) {
        return userRepository.findByPhonePrefix(prefix);
    }
}
