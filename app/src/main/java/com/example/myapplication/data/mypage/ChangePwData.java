package com.example.myapplication.data.mypage;

import com.google.gson.annotations.SerializedName;
// 0807 수정
public class ChangePwData {
    @SerializedName("UserPwd")
    private String UserPwd;

    @SerializedName("UserId")
    private String UserId;
    public ChangePwData(String UserPwd, String UserId) {

        this.UserPwd = UserPwd;
        this.UserId = UserId;

    }
}
