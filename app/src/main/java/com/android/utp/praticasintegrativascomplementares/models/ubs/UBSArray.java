package com.android.utp.praticasintegrativascomplementares.models.ubs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UBSArray {

    @SerializedName("next")
    @Expose
    private Integer next;

    @SerializedName("array")
    @Expose
    private List<UBS> array = null;

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List<UBS> getArray() {
        return array;
    }

    public void setArray(List<UBS> array) {
        this.array = array;
    }

}
