package com.android.utp.praticasintegrativascomplementares.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.model.User;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    private static String PROFILE_TAG = ProfileFragment.class.getSimpleName();

    ImageView profileImage;
    TextView userName;
    TextView userEmail;

    User user;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserProfile(AccessToken.getCurrentAccessToken());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImage = view.findViewById(R.id.profile_image);
        userName = view.findViewById(R.id.profile_username);
        userEmail = view.findViewById(R.id.profile_email);

        if (user != null) {
            userName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
            userEmail.setText(user.getEmail());
            Glide.with(this).load(user.getImageUrl()).into(profileImage);
        }


        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.default_toolbar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d(PROFILE_TAG, object.toString());
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
