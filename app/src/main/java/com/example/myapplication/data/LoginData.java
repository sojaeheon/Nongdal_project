package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("UserId")
    private String UserId;

    @SerializedName("UserPwd")
    private String UserPwd;

    public LoginData(String UserId, String UserPwd) {
        this.UserId = UserId;
        this.UserPwd = UserPwd;
    }
}
