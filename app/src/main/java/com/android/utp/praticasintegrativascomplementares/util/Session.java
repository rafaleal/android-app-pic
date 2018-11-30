package com.android.utp.praticasintegrativascomplementares.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private static final String FILE_PREFERENCES = "config_file";
    public static final String IS_LOGGED = "isLogged";

    private Session() {

    }

//    public Session(Context context) {
//        preferences = PreferenceManager.getDefaultSharedPreferences(context);
//    }

    public static void setLogged(Context context, boolean isLogged) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putBoolean(IS_LOGGED, isLogged)
                .apply();
    }

    public static boolean getLogged(Context context) {
       SharedPreferences preferences = context.getSharedPreferences(FILE_PREFERENCES, Context.MODE_PRIVATE);
       return preferences.getBoolean(IS_LOGGED, false);
    }

//    public static void setUbs(UBS ubs) {
//        Gson gson = new Gson();
//        String json = gson.toJson(ubs, UBS.class);
//
//        preferences.edit().putString("UBS", json);
//    }
//
//    public static UBS getUbs() {
//        String json = preferences.getString("UBS", "");
//
//        Gson gson = new Gson();
//        return gson.fromJson(json, UBS.class);
//    }


}
