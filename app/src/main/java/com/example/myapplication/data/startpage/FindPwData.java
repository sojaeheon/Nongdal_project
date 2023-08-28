package com.example.myapplication.data.startpage;

import com.google.gson.annotations.SerializedName;

public class FindPwData {
    @SerializedName("UserId")
    private String UserId;

    public FindPwData(String UserId) {
        this.UserId = UserId;
    }
}
