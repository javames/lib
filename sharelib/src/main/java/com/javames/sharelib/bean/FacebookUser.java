package com.javames.sharelib.bean;

import com.facebook.AccessToken;
import com.javames.sharelib.BaseUser;

/**
 * Created by changhong on 2019/4/3.
 */

public class FacebookUser extends BaseUser {

    private String profileName;
    private AccessToken accessToken;

    public FacebookUser() {
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

}
