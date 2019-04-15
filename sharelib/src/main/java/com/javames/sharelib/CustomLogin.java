package com.javames.sharelib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.javames.sharelib.bean.BaseLogin;
import com.javames.sharelib.util.Constants;
import com.javames.sharelib.util.LoginException;

/**
 * Created by changhong on 2019/4/3.
 */

public class CustomLogin extends BaseLogin {

    @Override
    public void login(LoginConfig config) {
        BaseUser user = config.getCallback().doCustomLogin();
        if (user != null) {
            // Save the user
            UserSessionManager.setUserSession(config.getActivity(), user);
            config.getCallback().onLoginSuccess(user);
        } else {
            config.getCallback().onLoginFailure(new LoginException("Custom login failed", LoginType.CustomLogin));
        }
    }

    @Override
    public void signup(LoginConfig config) {
        BaseUser user = config.getCallback().doCustomSignup();
        if (user != null) {
            // Save the user
            UserSessionManager.setUserSession(config.getActivity(), user);
            config.getCallback().onLoginSuccess(user);
        } else {
            config.getCallback().onLoginFailure(new LoginException("Custom signup failed", LoginType.CustomLogin));
        }
    }

    @Override
    public boolean logout(Context context) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(Constants.USER_TYPE);
            editor.remove(Constants.USER_SESSION);
            editor.apply();
            return true;
        } catch (Exception e) {
            Log.e("CustomLogin", e.getMessage());
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, LoginConfig config) {

    }

}
