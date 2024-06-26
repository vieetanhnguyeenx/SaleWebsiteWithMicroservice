package com.nva.InventoryService.exception;


import com.nva.InventoryService.model.ApiException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiRequestException extends RuntimeException{
    HttpStatus httpStatus;
    ApiException exception;

    public static ApiRequestException badRequest(List<String> message) {
        return ApiRequestException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exception(ApiException.builder()
                        .timestamp(ZonedDateTime.now(ZoneId.of("UTC")))
                        .status(HttpStatus.BAD_REQUEST)
                        .errors(message)
                        .path("")
                        .build())
                .build();
    }

    public static ApiRequestException notFound(List<String> message) {
        return ApiRequestException.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .exception(ApiException.builder()
                        .timestamp(ZonedDateTime.now(ZoneId.of("UTC")))
                        .status(HttpStatus.NOT_FOUND)
                        .errors(message)
                        .path("")
                        .build())
                .build();
    }

    public static ApiRequestException exception(List<String> message, HttpStatus status) {
        return ApiRequestException.builder()
                .httpStatus(status)
                .exception(ApiException.builder()
                        .timestamp(ZonedDateTime.now(ZoneId.of("UTC")))
                        .status(status)
                        .errors(message)
                        .path("")
                        .build())
                .build();
    }
}
