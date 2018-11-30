package com.android.utp.praticasintegrativascomplementares.models.pic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PICArray {

    @SerializedName("next")
    @Expose
    private Integer next;

    @SerializedName("array")
    @Expose
    private List<PIC> array = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public PICArray() {
    }

    /**
     *
     * @param next
     * @param array
     */
    public PICArray(Integer next, List<PIC> array) {
        super();
        this.next = next;
        this.array = array;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List<PIC> getArray() {
        return array;
    }

    public void setArray(List<PIC> array) {
        this.array = array;
    }
}
