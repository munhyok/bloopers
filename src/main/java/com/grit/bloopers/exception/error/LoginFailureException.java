package com.grit.bloopers.exception.error;

public class LoginFailureException extends RuntimeException{
    private static final String LOGIN_FAILURE = "로그인 실패";

    public LoginFailureException() {
        super(LOGIN_FAILURE);
    }
}
