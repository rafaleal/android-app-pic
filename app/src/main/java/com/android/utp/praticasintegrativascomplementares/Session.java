package com.android.utp.praticasintegrativascomplementares;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences preferences;

    public Session(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserId(String userId) {
        preferences.edit().putString("userId", userId).apply();
    }

    public String getUserId() {
        String userId = preferences.getString("userId", "");
        return userId;
    }

    public void setFirstname(String firstname) {
        preferences.edit().putString("firstname", firstname).apply();
    }

    public String getFirstname() {
        String firstname = preferences.getString("firstname", "");
        return firstname;
    }

    public void setLastname(String lastname) {
        preferences.edit().putString("lastname", lastname).apply();
    }

    public String getLastname() {
        String lastname = preferences.getString("lastname", "");
        return lastname;
    }

    public void setEmail(String email) {
        preferences.edit().putString("email", email).apply();
    }

    public String getEmail() {
        String email = preferences.getString("email", "");
        return email;
    }

    public void setProfileImageUrl(String imageUrl) {
        preferences.edit().putString("imageUrl", imageUrl).apply();
    }

    public String getProfileImageUrl() {
        String imageUrl = preferences.getString("imageUrl", "");
        return imageUrl;
    }

}
