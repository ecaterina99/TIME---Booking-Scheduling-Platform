package com.server.shared.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 * Universal API error response for all endpoints.
 */

@Data
@Builder
@Schema(description = "Standard API error response for all endpoints")
public class ApiError {

    @Schema(description = "Unique error code or type")
    private String error;

    @Schema(description = "Human-readable error message")
    private String message;

    @Schema(description = "HTTP status code")
    private int status;

    @Schema(description = "Timestamp when the error occurred")
    private LocalDateTime timestamp;

    @Schema(description = "Field-level validation errors (present only for validation issues)", nullable = true)
    private Map<String, String> fieldErrors;

    @Schema(description = "Additional contextual details (optional)", nullable = true)
    private Map<String, Object> details;

    public static ApiError of(HttpStatus status, String error, String message) {
        return ApiError.builder()
                .status(status.value())
                .error(error)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
