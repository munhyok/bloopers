package com.grit.bloopers.dto.response;

import com.grit.bloopers.utils.HttpRequestUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class CommonResponse {
    private final String status;
    private final int code;
    private final String message;
    private final String path;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();


    public static ResponseEntity<CommonResponse> toResponseEntity(HttpStatus httpStatus, Exception e) {
        return ResponseEntity.status(httpStatus)
                .body(
                        CommonResponse.builder()
                                .status(httpStatus.name())
                                .code(httpStatus.value())
                                .message(e.getMessage())
                                .path(HttpRequestUtils.getRequest().getRequestURI())
                                .build()
                );


    }
}
