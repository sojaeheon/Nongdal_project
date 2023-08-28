package com.example.myapplication.data.startpage;

import com.google.gson.annotations.SerializedName;
// 0807 수정
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
