package com.android.utp.praticasintegrativascomplementares;

public class TherapyItem {

    private String mTherapyName;

    private int mImageResourceId;

    public TherapyItem(String mTherapyName, int mImageResourceId) {
        this.mTherapyName = mTherapyName;
        this.mImageResourceId = mImageResourceId;
    }

    public String getTherapyName() {
        return mTherapyName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}
