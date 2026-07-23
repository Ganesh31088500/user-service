package com.apiwatch.user_service.service;



import java.util.List;
import java.util.UUID;

import com.apiwatch.user_service.dto.request.CreateUserProfileRequest;
import com.apiwatch.user_service.dto.request.UpdateUserProfileRequest;
import com.apiwatch.user_service.dto.response.UserResponse;
import com.apiwatch.user_service.exceptions.UserAlreadyExistsException;

public interface UserService {

//    UserResponse createUser(CreateUserProfileRequest request);
//
//    UserResponse getUser(UUID id);
//
//    UserResponse getUserByAuthUserId(UUID authUserId);
//
//    List<UserResponse> getAllUsers();
//
//    UserResponse updateUser(UUID id,
//                            UpdateUserProfileRequest request);
//
//    void deleteUser(UUID id);
	
	 void createUserProfile(CreateUserProfileRequest request) throws UserAlreadyExistsException;

	    UserResponse getMyProfile();

	    UserResponse updateMyProfile(UpdateUserProfileRequest request);

}
