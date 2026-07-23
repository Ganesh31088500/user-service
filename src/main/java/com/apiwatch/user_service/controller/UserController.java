package com.apiwatch.user_service.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.apiwatch.user_service.dto.request.CreateUserProfileRequest;
import com.apiwatch.user_service.dto.request.UpdateUserProfileRequest;
import com.apiwatch.user_service.dto.response.ApiResponse;
import com.apiwatch.user_service.dto.response.UserResponse;
import com.apiwatch.user_service.exceptions.UserAlreadyExistsException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
   @Autowired
    private  com.apiwatch.user_service.service.UserService userService;

   @GetMapping("/me")
   public ResponseEntity<ApiResponse<UserResponse>> getProfile() {

       return ResponseEntity.ok(
               ApiResponse.success(userService.getMyProfile())
       );

   }
   @PostMapping("/profile")
   public ResponseEntity<ApiResponse<Void>> createProfile(
           @Valid @RequestBody CreateUserProfileRequest request) throws UserAlreadyExistsException {

       userService.createUserProfile(request);

       return ResponseEntity.status(HttpStatus.CREATED)
               .body(ApiResponse.success("User profile created successfully."));
   }
   @PutMapping("/me")
   public ResponseEntity<ApiResponse<UserResponse>> updateProfile(
           @Valid @RequestBody UpdateUserProfileRequest request) {

       return ResponseEntity.ok(
               ApiResponse.success(userService.updateMyProfile(request))
       );

   }

}