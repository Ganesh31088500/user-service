package com.apiwatch.user_service.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import com.apiwatch.user_service.enums.Role;
import com.apiwatch.user_service.enums.UserStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	  private UUID id;

	    private UUID authUserId;

	    private String username;

	    private String email;

	    private String firstName;

	    private String lastName;

	    private String phoneNumber;

	    private String avatarUrl;

	    private String role;

	    private Boolean enabled;

}
