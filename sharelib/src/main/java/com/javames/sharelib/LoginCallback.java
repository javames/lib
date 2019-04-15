package com.javames.sharelib;

import com.javames.sharelib.util.LoginException;

/**
 * Created by changhong on 2019/4/3.
 */

public interface LoginCallback {

    void onLoginSuccess(BaseUser user);

    void onLoginFailure(LoginException e);

    BaseUser doCustomLogin();

    BaseUser doCustomSignup();
}
