package com.android.utp.praticasintegrativascomplementares.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    public static final String IS_LOGGED = "isLogged";
    public static final String USER_ID = "userId";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String IMAGE_URL = "imageUrl";
    private static SharedPreferences preferences;

    public Session(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLogged(boolean isLogged) {
        preferences.edit().putBoolean(IS_LOGGED, isLogged).apply();
    }

    public static boolean getLogged() {
       return preferences.getBoolean(IS_LOGGED, false);
    }

    public static void setUserId(String userId) {
        preferences.edit().putString(USER_ID, userId).apply();
    }

    public static String getUserId() {
        String userId = preferences.getString(USER_ID, "");
        return userId;
    }

    public static void setFirstname(String firstname) {
        preferences.edit().putString(FIRSTNAME, firstname).apply();
    }

    public static String getFirstname() {
        String firstname = preferences.getString(FIRSTNAME, "");
        return firstname;
    }

    public static void setLastname(String lastname) {
        preferences.edit().putString(LASTNAME, lastname).apply();
    }

    public String getLastname() {
        String lastname = preferences.getString(LASTNAME, "");
        return lastname;
    }

    public static void setEmail(String email) {
        preferences.edit().putString(EMAIL, email).apply();
    }

    public static String getEmail() {
        String email = preferences.getString(EMAIL, "");
        return email;
    }

    public static void setProfileImageUrl(String imageUrl) {
        preferences.edit().putString(IMAGE_URL, imageUrl).apply();
    }

    public static String getProfileImageUrl() {
        String imageUrl = preferences.getString(IMAGE_URL, "");
        return imageUrl;
    }

}
