package com.apiwatch.user_service.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apiwatch.user_service.dto.request.UpdateUserProfileRequest;
import com.apiwatch.user_service.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
   @Autowired
    private  com.apiwatch.user_service.service.UserService userService;

    @PostMapping
    public ResponseEntity<com.apiwatch.user_service.dto.response.UserResponse> createUser(
            @Valid @RequestBody com.apiwatch.user_service.dto.request.CreateUserProfileRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                userService.getUser(id)
        );
    }

    @GetMapping("/auth/{authUserId}")
    public ResponseEntity<UserResponse> getUserByAuthId(
            @PathVariable UUID authUserId) {

        return ResponseEntity.ok(
                userService.getUserByAuthUserId(authUserId)
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserProfileRequest request) {

        return ResponseEntity.ok(
                userService.updateUser(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable UUID id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}