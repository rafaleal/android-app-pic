package com.android.utp.praticasintegrativascomplementares;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    LoginButton loginButton;
    CallbackManager callbackManager;

    private Intent intent;

    private User user;

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent = new Intent(this, MapsActivity.class);

        callbackManager = CallbackManager.Factory.create();

        session = new Session(this);

        loginButton = findViewById(R.id.login_button);

        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        Log.d(LOG_TAG, "Logged in Status: " + loggedOut);

        if(!loggedOut) {
            Log.d(LOG_TAG, "Username: " + Profile.getCurrentProfile().getName());
            getUserProfile(AccessToken.getCurrentAccessToken());
        }

        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(LOG_TAG, "Logged in Facebook account");
                Log.d(LOG_TAG, "User id: " + user.getId());
                Log.d(LOG_TAG, "User first name: " + user.getFirstName());
                Log.d(LOG_TAG, "User last name: " + user.getLastName());
                Log.d(LOG_TAG, "User email: " + user.getEmail());
                Log.d(LOG_TAG, "User image resource url: " + user.getImageUrl());

                session.setUserId(user.getId());
                session.setFirstname(user.getFirstName());
                session.setLastname(user.getLastName());
                session.setEmail(user.getEmail());
                session.setProfileImageUrl(user.getImageUrl());

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
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d(LOG_TAG, object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String email = object.getString("email");
                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                            user = new User(id, first_name, last_name, email, image_url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

}

