package com.apiwatch.user_service.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.apiwatch.user_service.dto.request.CreateUserProfileRequest;
import com.apiwatch.user_service.dto.response.UserResponse;
import com.apiwatch.user_service.entity.User;
import com.apiwatch.user_service.enums.UserStatus;

@Mapper(componentModel = "spring",
        imports = UserStatus.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "status",
            expression = "java(UserStatus.ACTIVE)")
    User toEntity(CreateUserProfileRequest request);

    UserResponse toResponse(User user);
    



}