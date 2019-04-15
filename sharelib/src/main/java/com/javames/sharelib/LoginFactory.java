package com.javames.sharelib;

import android.util.Log;

import com.javames.sharelib.bean.BaseLogin;

/**
 * Created by changhong on 2019/4/3.
 */

public class LoginFactory {

    public static BaseLogin build(LoginType loginType) {
        switch (loginType) {
            case Facebook:
                return new FacebookLogin();
            case Google:
                Log.i("test","Google");
                return new GoogleLogin();
            case CustomLogin:
                return new CustomLogin();
            default:
                // To avoid null pointers
                return new CustomLogin();
        }
    }
}
