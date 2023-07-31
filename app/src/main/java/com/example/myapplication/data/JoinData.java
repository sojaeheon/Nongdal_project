package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("UserId")
    private String UserId;

    @SerializedName("UserPwd")
    private String UserPwd;

    @SerializedName("UserEmail")
    private String UserEmail;



    public JoinData(String UserId, String UserPwd, String UserEmail) {
        this.UserId = UserId;
        this.UserPwd = UserPwd;
        this.UserEmail = UserEmail;

    }
}
