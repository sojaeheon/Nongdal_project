package com.example.myapplication.data.startpage;
// 0807 수정
import com.google.gson.annotations.SerializedName;

public class CheckIdResponse {
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
