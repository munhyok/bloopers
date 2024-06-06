package com.grit.bloopers.exception.error;

public class LoginRequiredException extends RuntimeException{
    private static final String NEED_LOGIN = "로그인이 필요합니다.";

    public LoginRequiredException() {
        super(NEED_LOGIN);
    }
}
