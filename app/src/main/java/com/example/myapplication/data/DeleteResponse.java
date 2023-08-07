package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class DeleteResponse {

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
