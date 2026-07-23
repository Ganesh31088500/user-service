package com.apiwatch.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiwatch.user_service.dto.request.CreateUserProfileRequest;
import com.apiwatch.user_service.dto.response.ApiResponse;
import com.apiwatch.user_service.exceptions.UserAlreadyExistsException;
import com.apiwatch.user_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users/internal")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @PostMapping("/profile")
    public ResponseEntity<ApiResponse<Void>> createProfile(
            @Valid @RequestBody CreateUserProfileRequest request) throws UserAlreadyExistsException {

        userService.createUserProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User profile created successfully"));
    }

}