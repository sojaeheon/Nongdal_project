package com.example.myapplication.data.mypage;

import com.google.gson.annotations.SerializedName;

public class ChangeEmailData {

    @SerializedName("UserEmail")
    private String UserEmail;

    @SerializedName("UserId")
    private String UserId;

    public ChangeEmailData(String UserEmail, String UserId) {

        this.UserEmail = UserEmail;
        this.UserId = UserId;

    }
}
