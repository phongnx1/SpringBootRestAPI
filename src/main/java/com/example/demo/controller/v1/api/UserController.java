package com.example.demo.controller.v1.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
public class UserController {
 
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users/all")
    public List<User> getAllUsers() {
         return userRepository.findAll();
    }

    @GetMapping("/users/")
    public List<User> getAllUsersPaging(
      @RequestParam(defaultValue = "1") Integer page
    ) {
      Pageable paging = PageRequest.of(page - 1, 5);
         return userRepository.findAllUsers(paging);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
      @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
         User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users")
    public User createUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") Long userId,
      @Validated @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + userId));
  
        user.setStatus(userDetails.getStatus());
        user.setUserType(userDetails.getUserType());
        user.setEmailAddress(userDetails.getEmailAddress());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setLastModifiedDate(new Date());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(
         @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
         User user = userRepository.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + userId));

        userRepository.delete(user);
         Map<String, Boolean> response = new HashMap<>();
         response.put("deleted", Boolean.TRUE);
         return response;
    }
}
