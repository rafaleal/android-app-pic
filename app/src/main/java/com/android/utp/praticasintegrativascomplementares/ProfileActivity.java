package com.android.utp.praticasintegrativascomplementares;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView userName;
    TextView userEmail;

    User user;

    Session session;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profile_image);
        userName = findViewById(R.id.profile_user_name);
        userEmail = findViewById(R.id.profile_email);

        session = new Session(this);

        String userId = session.getUserId();
        String userFirstname = session.getFirstname();
        String userLastname = session.getLastname();
        String email = session.getEmail();
        String userImageUrl = session.getProfileImageUrl();

        user = new User(userId, userFirstname, userLastname, email, userImageUrl);

        userName.setText(String.format("%s %s", userFirstname, userLastname));
        userEmail.setText(email);
        Glide.with(this).load(userImageUrl).into(profileImage);
    }
}
