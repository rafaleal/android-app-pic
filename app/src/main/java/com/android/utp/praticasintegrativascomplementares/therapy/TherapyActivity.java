package com.android.utp.praticasintegrativascomplementares.therapy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.utp.praticasintegrativascomplementares.R;

public class TherapyActivity extends AppCompatActivity {

    private static final String LOG_TAG = TherapyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_therapy);
        Log.d(LOG_TAG, "Inside onCreate");
    }
}
