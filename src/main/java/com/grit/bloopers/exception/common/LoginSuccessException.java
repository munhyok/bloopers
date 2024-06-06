package com.grit.bloopers.exception.common;

public class LoginSuccessException extends RuntimeException{
    private static final String LOGIN_SUCCESS = "로그인 성공";

    public LoginSuccessException() {
        super(LOGIN_SUCCESS);
    }

}
