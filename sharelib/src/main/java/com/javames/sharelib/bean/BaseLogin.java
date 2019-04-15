package com.javames.sharelib.bean;

import android.content.Context;
import android.content.Intent;

import com.javames.sharelib.LoginConfig;

/**
 * Created by changhong on 2019/4/3.
 */

public abstract class BaseLogin {

    public abstract void login(LoginConfig config);

    public abstract void signup(LoginConfig config);

    public abstract boolean logout(Context context);

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data,LoginConfig config);
}
