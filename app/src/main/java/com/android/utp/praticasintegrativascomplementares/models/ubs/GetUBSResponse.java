package com.android.utp.praticasintegrativascomplementares.models.ubs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUBSResponse {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("object")
    @Expose
    private UBSArray object;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetUBSResponse() {
    }

    /**
     *
     * @param message
     * @param status
     * @param object
     * @param date
     */
    public GetUBSResponse(String date, Integer status, String message, UBSArray object) {
        super();
        this.date = date;
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UBSArray getObjectPhp() {
        return object;
    }

    public void setObjectPhp(UBSArray object) {
        this.object = object;
    }

}
