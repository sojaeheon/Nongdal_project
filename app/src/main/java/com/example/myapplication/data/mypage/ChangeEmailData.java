package com.example.myapplication.data.mypage;

import com.google.gson.annotations.SerializedName;

public class ChangeEmailData {

    @SerializedName("UserEmail")
    private String UserEmail;

    public ChangeEmailData(String UserEmail) {

        this.UserEmail = UserEmail;

    }
}
