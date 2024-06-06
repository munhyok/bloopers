package com.grit.bloopers.controller.handler;



import com.grit.bloopers.dto.response.CommonResponse;
import com.grit.bloopers.exception.common.LoginSuccessException;
import com.grit.bloopers.exception.common.RegisterCompleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class CommonController {

    @ExceptionHandler(LoginSuccessException.class)
    public ResponseEntity handleLoginSuccessException(LoginSuccessException e) {
        return CommonResponse.toResponseEntity(HttpStatus.OK, e);
    }

    @ExceptionHandler(RegisterCompleteException.class)
    public ResponseEntity handleRegisterCompleteException(RegisterCompleteException e) {
        return CommonResponse.toResponseEntity(HttpStatus.CREATED, e);
    }


}
