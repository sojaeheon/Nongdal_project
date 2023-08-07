package com.example.myapplication.data;
// 0807 수정
import com.google.gson.annotations.SerializedName;

public class CheckIdData {
    @SerializedName("UserId")
    private String UserId;

    public CheckIdData(String UserId) {
        this.UserId = UserId;
    }
}
