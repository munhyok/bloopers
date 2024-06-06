package com.grit.bloopers.exception.common;

public class RegisterCompleteException extends RuntimeException{
    private static final String REGISTER_COMPLETEE = "회원가입 성공";

    public RegisterCompleteException() {
        super(REGISTER_COMPLETEE);
    }
}
