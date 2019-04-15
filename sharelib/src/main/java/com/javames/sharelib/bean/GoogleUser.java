package com.javames.sharelib.bean;

import com.javames.sharelib.BaseUser;

/**
 * Created by changhong on 2019/4/3.
 */

public class GoogleUser extends BaseUser {

    private String displayName;
    private String photoUrl;
    private String idToken;

    public GoogleUser() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
