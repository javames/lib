package com.javames.sharelib.util;

import android.util.Log;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.javames.sharelib.BaseUser;
import com.javames.sharelib.bean.FacebookUser;
import com.javames.sharelib.bean.GoogleUser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changhong on 2019/4/3.
 */

public class UserUtil {
    private static final String TAG="UserUtil";
    public static GoogleUser populateGoogleUser(GoogleSignInAccount account){
        //Create a new google user
        GoogleUser googleUser = new GoogleUser();
        //populate the user
        Log.i(TAG,"displayName: "+account.getDisplayName());
        Log.i(TAG,"email: "+account.getEmail());
        Log.i(TAG,"idToken: "+account.getIdToken());
        Log.i(TAG,"userId: "+account.getId());
        googleUser.setDisplayName(account.getDisplayName());
        if (account.getPhotoUrl() != null) {
            Log.i(TAG,"photoUrl: "+account.getPhotoUrl());
            googleUser.setPhotoUrl(account.getPhotoUrl().toString());
        }
        googleUser.setEmail(account.getEmail());
        googleUser.setIdToken(account.getIdToken());
        googleUser.setUserId(account.getId());
        if (account.getAccount() != null) {
            googleUser.setUsername(account.getAccount().name);
            Log.i(TAG,"username: "+account.getAccount().name);
        }

        //return the populated google user
        return googleUser;
    }

    public static FacebookUser populateFacebookUser(JSONObject object, AccessToken accessToken){
        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setGender(-1);
        Log.d(TAG, "returning login object " + object.toString());
        Log.i(TAG,"accessToken: "+accessToken);
        Log.i(TAG,"id: "+accessToken.getUserId());
        facebookUser.setAccessToken(accessToken);
        try {
            if (object.has(Constants.FacebookFields.EMAIL)){
                Log.i(TAG,"email: "+object.getString(Constants.FacebookFields.EMAIL));
                facebookUser.setEmail(object.getString(Constants.FacebookFields.EMAIL));
            }
            if (object.has(Constants.FacebookFields.BIRTHDAY)){
                Log.i(TAG,"birthday: "+object.getString(Constants.FacebookFields.BIRTHDAY));
                facebookUser.setBirthday(object.getString(Constants.FacebookFields.BIRTHDAY));
            }
            if (object.has(Constants.FacebookFields.GENDER)) {
                try {
                    Constants.Gender gender = Constants.Gender.valueOf(object.getString(Constants.FacebookFields.GENDER));
                    switch (gender) {
                        case male:
                            facebookUser.setGender(0);
                            break;
                        case female:
                            facebookUser.setGender(1);
                            break;
                    }
                } catch (Exception e) {
                    //if gender is not in the enum it is already set to unspecified value (-1)
                    Log.e("UserUtil", e.getMessage());
                }
            }
            if (object.has(Constants.FacebookFields.LINK)){
                Log.i(TAG,"ProfileLink: "+object.getString(Constants.FacebookFields.LINK));
                facebookUser.setProfileLink(object.getString(Constants.FacebookFields.LINK));
            }
            if (object.has(Constants.FacebookFields.ID)){
                Log.i(TAG,"id: "+object.getString(Constants.FacebookFields.ID));
                facebookUser.setProfileLink(object.getString(Constants.FacebookFields.ID));
            }
            if (object.has(Constants.FacebookFields.NAME)){
                Log.i(TAG,"ProfileName: "+object.getString(Constants.FacebookFields.NAME));
                facebookUser.setProfileLink(object.getString(Constants.FacebookFields.NAME));
            }
            if (object.has(Constants.FacebookFields.FIRST_NAME)){
                Log.i(TAG,"FirstName: "+object.getString(Constants.FacebookFields.FIRST_NAME));
                facebookUser.setFirstName(object.getString(Constants.FacebookFields.FIRST_NAME));
            }
            if (object.has(Constants.FacebookFields.MIDDLE_NAME)){
                Log.i(TAG,"MiddleName: "+object.getString(Constants.FacebookFields.MIDDLE_NAME));
                facebookUser.setMiddleName(object.getString(Constants.FacebookFields.MIDDLE_NAME));
            }
            if (object.has(Constants.FacebookFields.LAST_NAME)){
                Log.i(TAG,"lastName: "+object.getString(Constants.FacebookFields.LAST_NAME));
                facebookUser.setMiddleName(object.getString(Constants.FacebookFields.LAST_NAME));
            }
        } catch (JSONException e){
            Log.e("UserUtil", e.getMessage());
            facebookUser = null;
        }
        return facebookUser;
    }

    public static BaseUser populateCustomUser(String username, String email, String userId){
        BaseUser user = new BaseUser();
        user.setEmail(email);
        user.setUsername(username);
        user.setUserId(userId);
        return user;
    }
}
