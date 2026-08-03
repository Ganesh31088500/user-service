package com.apiwatch.user_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.apiwatch.user_service.dto.request.AssignRoleRequest;
import com.apiwatch.user_service.dto.request.CreateUserProfileRequest;
import com.apiwatch.user_service.dto.request.UpdateUserProfileRequest;
import com.apiwatch.user_service.dto.response.UserResponse;
import com.apiwatch.user_service.entity.User;
import com.apiwatch.user_service.enums.Role;
import com.apiwatch.user_service.enums.UserStatus;
import com.apiwatch.user_service.exception.UserNotFoundException;
import com.apiwatch.user_service.exceptions.UserAlreadyExistsException;
import com.apiwatch.user_service.mapper.UserMapper;
import com.apiwatch.user_service.repository.UserRepository;
import com.apiwatch.user_service.security.SecurityUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final SecurityUtils securityUtils;

    @Override
    public void createUserProfile(CreateUserProfileRequest request) throws UserAlreadyExistsException {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            throw  new UserAlreadyExistsException(
                    "User already exists with email : " + request.getEmail());  //Unhandled exception type UserAlreadyExistsException

        }
        Role role = Role.valueOf(
        	    request.getRole()
        	);
        
        log.info(" Role " + role);
        User user =  User.builder()
                .authUserId(request.getAuthUserId())
                .username(request.getUsername())
                .email(request.getEmail())
                .role(role.name())
                .status(UserStatus.ACTIVE)
                .enabled(true)
                .profilePicture("/avatars/default.png")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

    }
    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();

    }
    @Override
    public UserResponse getMyProfile() {

    	String username = securityUtils.getCurrentUsername();

        User user = userRepository

                .findByUsername(username)

                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return userMapper.toResponse(user);

    }

    @Override
    public UserResponse updateMyProfile(UpdateUserProfileRequest request) {

    	 String username = securityUtils.getCurrentUsername();

    	    User user = userRepository

    	            .findByUsername(username)

    	            .orElseThrow(() ->
    	                    new UserNotFoundException("User not found"));

    	    user.setFirstName(request.getFirstName());

    	    user.setLastName(request.getLastName());

    	    user.setPhoneNumber(request.getPhoneNumber());

    	    User updatedUser = userRepository.save(user);

    	    return userMapper.toResponse(updatedUser);

    }

    @Override
    public UserResponse getUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toResponse(user);
    }
    @Override
    @Transactional
    public void updateRole(UUID authUserId, AssignRoleRequest request) {

        User user = userRepository.findByAuthUserId(authUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("In service update: " + user.getRole());

        user.setRole(request.getRole());

        userRepository.save(user);
        System.out.println("After update: " + user.getRole());

    }

}