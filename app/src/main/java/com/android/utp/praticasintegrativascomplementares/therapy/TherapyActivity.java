package com.android.utp.praticasintegrativascomplementares.therapy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.models.pic.PIC;

import org.parceler.Parcels;

public class TherapyActivity extends AppCompatActivity {

    private static final String LOG_TAG = TherapyActivity.class.getSimpleName();
    PIC pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_therapy);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pic = Parcels.unwrap(extras.getParcelable("PIC"));
        Log.d(LOG_TAG, "Inside onCreate: " + pic.toString());
    }
}
