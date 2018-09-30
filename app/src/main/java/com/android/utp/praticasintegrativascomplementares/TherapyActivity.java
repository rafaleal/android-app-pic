package com.android.utp.praticasintegrativascomplementares;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TherapyActivity extends AppCompatActivity {

    private static final String LOG_TAG = TherapyItemAdapter.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);
        Log.d(LOG_TAG, "Inside onCreate");
    }
}
