package com.javames.sharelib.util;

import com.javames.sharelib.LoginType;

/**
 * Created by changhong on 2019/4/3.
 */

public class LoginException extends Exception{

    private LoginType loginType;

    public LoginException(String message, LoginType loginType) {
        super(message);
        this.loginType = loginType;
    }

    public LoginException(String message, Throwable cause, LoginType loginType) {
        super(message, cause);
        this.loginType = loginType;
    }

    public LoginType getLoginType() {
        return loginType;
    }
}
