package com.android.utp.praticasintegrativascomplementares.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.home.HomeActivity;
import com.android.utp.praticasintegrativascomplementares.models.User;
import com.android.utp.praticasintegrativascomplementares.util.Session;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    LoginButton loginButton;
    CallbackManager callbackManager;

    private Intent intent;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent = new Intent(this, HomeActivity.class);

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);

        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        Log.d(LOG_TAG, "Logged in Status: " + !loggedOut);

        if(!loggedOut) {
            Log.d(LOG_TAG, "Calling getUserProfile");
            getUserProfile(AccessToken.getCurrentAccessToken());
//            startActivity(intent);
        }

        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            if(user != null) {
                Log.d(LOG_TAG, "Logged in Facebook account");
                Log.d(LOG_TAG, "User id: " + user.getId());
                Log.d(LOG_TAG, "User first name: " + user.getFirstName());
                Log.d(LOG_TAG, "User last name: " + user.getLastName());
                Log.d(LOG_TAG, "User email: " + user.getEmail());
                Log.d(LOG_TAG, "User image resource url: " + user.getImageUrl());
            }

                Session.setLogged(getApplicationContext(), true);

                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.i(LOG_TAG, "Error logging in Facebook account");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, (object, response) -> {
//                    Log.d(LOG_TAG, object.toString());
                    try {
                        String first_name = object.getString("first_name");
                        String last_name = object.getString("last_name");
                        String email = object.getString("email");
                        String id = object.getString("id");
                        String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                        user = new User(id, first_name, last_name, email, image_url);
                        Session.setUser(getApplicationContext(), user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

}

