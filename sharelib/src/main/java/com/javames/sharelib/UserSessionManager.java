package com.javames.sharelib;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.javames.sharelib.bean.FacebookUser;
import com.javames.sharelib.bean.GoogleUser;
import com.javames.sharelib.util.Constants;

/**
 * Created by changhong on 2019/4/3.
 */

public class UserSessionManager {

    /**
     This static method can be called to get the logged in user.
     It reads from the shared preferences and builds a SmartUser object and returns it.
     If no user is logged in it returns null
     */
    public static BaseUser getCurrentUser(Context context){
        BaseUser smartUser = null;
        SharedPreferences preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String sessionUser = preferences.getString(Constants.USER_SESSION, Constants.DEFAULT_SESSION_VALUE);
        String user_type = preferences.getString(Constants.USER_TYPE, Constants.CUSTOMUSERFLAG);
        if (!sessionUser.equals(Constants.DEFAULT_SESSION_VALUE)) {
            try {
                switch (user_type) {
                    case Constants.FACEBOOKFLAG:
                        smartUser = gson.fromJson(sessionUser, FacebookUser.class);
                        break;
                    case Constants.GOOGLEFLAG:
                        smartUser = gson.fromJson(sessionUser, GoogleUser.class);
                        break;
                    default:
                        smartUser = gson.fromJson(sessionUser, BaseUser.class);
                        break;
                }
            } catch (Exception e) {
                Log.e("GSON", e.getMessage());
            }
        }
        return smartUser;
    }

    /**
     This method sets the session object for the current logged in user.
     This is called from inside the SmartLoginActivity to save the
     current logged in user to the shared preferences.
     */
    static boolean setUserSession(Context context, BaseUser smartUser){
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        if(smartUser != null) {
            try {
                preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);
                editor = preferences.edit();

                if (smartUser instanceof FacebookUser) {
                    editor.putString(Constants.USER_TYPE, Constants.FACEBOOKFLAG);
                } else if (smartUser instanceof GoogleUser) {
                    editor.putString(Constants.USER_TYPE, Constants.GOOGLEFLAG);
                } else {
                    editor.putString(Constants.USER_TYPE, Constants.CUSTOMUSERFLAG);
                }
                Gson gson = new Gson();
                String sessionUser = gson.toJson(smartUser);
                Log.d("GSON", sessionUser);
                editor.putString(Constants.USER_SESSION, sessionUser);
                editor.apply();
                return true;
            } catch (Exception e) {
                Log.e("Session Error", e.getMessage());
                return false;
            }
        } else {
            Log.e("Session Error", "User is null");
            return false;
        }
    }

}
