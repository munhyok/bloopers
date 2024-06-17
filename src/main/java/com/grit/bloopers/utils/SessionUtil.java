package com.grit.bloopers.utils;


/*
    SessionUtil
    코드 재사용성 높이기
 */

import jakarta.servlet.http.HttpSession;


public class SessionUtil {


    private static final String LOGIN_USERS_ID = "LOGIN_USERS_ID";

    private SessionUtil() {
    }

    public static void setLoginUsersId(HttpSession session, int id) {
        session.setAttribute(LOGIN_USERS_ID, id);

    }

    public static int getLoginUsersId(HttpSession session) {

        return (int) session.getAttribute(LOGIN_USERS_ID);
    }

    public static int getLoginUserId() {

        Object result = HttpRequestUtils.getRequest().getSession().getAttribute(LOGIN_USERS_ID);

        if (result == null) {
            return -1;
        }

        return (int) result;
    }

    public static void logoutUser(HttpSession session) {
        session.removeAttribute(LOGIN_USERS_ID);
    }

}
