package com.system.arts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.arts.entity.User;
import com.system.arts.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }

    public User getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    public User createUser(User user) {
        String username = user.getUsername();
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
        return userRepository.save(user);
    }

    public User updateUser(User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            String updatedUserName = updatedUser.getUsername();
            if (updatedUserName != null && !updatedUserName.equals(existingUser.getUsername())) {
                if (userRepository.findByUsername(updatedUserName) != null) {
                    throw new IllegalArgumentException("Username already exists");
                }
            }
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAge(updatedUser.getAge());
            existingUser.setMoney(updatedUser.getMoney());
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found with id " + updatedUser.getId());
        }
    }
    

    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
        userRepository.delete(user);
    }
}
