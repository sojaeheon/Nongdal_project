package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class CheckIdData {
    @SerializedName("UserId")
    private String UserId;

    public CheckIdData(String UserId) {
        this.UserId = UserId;
    }
}
