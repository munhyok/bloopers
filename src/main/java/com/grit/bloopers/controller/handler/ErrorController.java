package com.grit.bloopers.controller.handler;




import com.grit.bloopers.dto.response.CommonResponse;
import com.grit.bloopers.exception.error.LoginFailureException;
import com.grit.bloopers.exception.error.LoginRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(LoginRequiredException.class)
    public ResponseEntity<CommonResponse> handleLoginRequiredException(LoginRequiredException e) {
        return CommonResponse.toResponseEntity(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(LoginFailureException.class)
    public ResponseEntity<CommonResponse> handleLoginFailureException(LoginFailureException e) {
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, e);
    }
}
