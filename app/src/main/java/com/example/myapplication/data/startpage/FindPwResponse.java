package com.example.myapplication.data.startpage;

import com.google.gson.annotations.SerializedName;

public class FindPwResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("rUserPw")
    private String rUserPw;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getrUserPw() { return rUserPw; }
}
