package com.android.utp.praticasintegrativascomplementares.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.utp.praticasintegrativascomplementares.home.HomeActivity;
import com.android.utp.praticasintegrativascomplementares.login.LoginActivity;
import com.android.utp.praticasintegrativascomplementares.util.Session;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Intent intent;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new Session(this);

        boolean isLogged = Session.getLogged();
        Log.d(TAG, "onCreate: isLogged = " + isLogged);

        if (isLogged) {
            Log.d(TAG, "onCreate: redirect to HomeActivity");

            intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.d(TAG, "onCreate: redirect to LoginActivity");

            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
