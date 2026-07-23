package com.apiwatch.user_service.exception;

import lombok.Builder;
import lombok.Data;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private String errorCode;

    private String message;

    private String path;

    private LocalDateTime timestamp;



}
