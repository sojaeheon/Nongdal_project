package com.example.myapplication.data.mypage;

import com.google.gson.annotations.SerializedName;
// 0807 수정
public class ChangePwResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
