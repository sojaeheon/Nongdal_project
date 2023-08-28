package com.example.myapplication.data.startpage;

import com.google.gson.annotations.SerializedName;

public class FindIdResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    @SerializedName("rUserId")
    private String rUserId;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getrUserId() { return rUserId; }
}
