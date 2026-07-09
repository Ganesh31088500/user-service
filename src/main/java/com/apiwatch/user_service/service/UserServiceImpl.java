package com.apiwatch.user_service.service;



import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apiwatch.user_service.dto.request.CreateUserProfileRequest;
import com.apiwatch.user_service.dto.request.UpdateUserProfileRequest;
import com.apiwatch.user_service.dto.response.UserResponse;
import com.apiwatch.user_service.entity.User;
import com.apiwatch.user_service.mapper.UserMapper;
import com.apiwatch.user_service.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
@Autowired
    private  UserRepository repository;

    private  UserMapper mapper;

    @Override
    public UserResponse createUser(CreateUserProfileRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (repository.existsByAuthUserId(request.getAuthUserId())) {
            throw new RuntimeException("User already exists");
        }

        User user = mapper.toEntity(request);

        User savedUser = repository.save(user);

        return mapper.toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUser(UUID id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return mapper.toResponse(user);

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByAuthUserId(UUID authUserId) {

        User user = repository.findByAuthUserId(authUserId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return mapper.toResponse(user);

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public UserResponse updateUser(UUID id,
                                   UpdateUserProfileRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfilePicture(request.getProfilePicture());

        return mapper.toResponse(repository.save(user));

    }

    @Override
    public void deleteUser(UUID id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        repository.delete(user);

    }



}
