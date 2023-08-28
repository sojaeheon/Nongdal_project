package com.example.myapplication.data.startpage;

import com.google.gson.annotations.SerializedName;

public class FindIdData {
    @SerializedName("UserEmail")
    private String UserEmail;

    public FindIdData(String UserEmail) {
        this.UserEmail = UserEmail;
    }
}
