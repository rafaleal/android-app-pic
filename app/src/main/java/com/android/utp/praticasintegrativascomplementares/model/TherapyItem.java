package com.android.utp.praticasintegrativascomplementares.model;

public class TherapyItem {

    private String therapyName = "";

    private int imageResourceId = 0;

    public TherapyItem() {
        this("", 0);
    }

    public TherapyItem(String mTherapyName, int mImageResourceId) {
        this.therapyName = mTherapyName;
        this.imageResourceId = mImageResourceId;
    }

    public String getTherapyName() {
        return therapyName;
    }

    public void setTherapyName(String name) {
        this.therapyName = name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int image) {
        this.imageResourceId = image;
    }
}
