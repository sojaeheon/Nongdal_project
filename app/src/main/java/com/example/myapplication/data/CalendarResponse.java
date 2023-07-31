package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class CalendarResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("sText")
    private String sText;

    @SerializedName("sinput1")
    private String sinput1;

    @SerializedName("sinput2")
    private String sinput2;

    @SerializedName("sinput3")
    private String sinput3;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getsText(){ return sText; }

    public String getsinput1() { return sinput1; }
    public String getsinput2() { return sinput2; }
    public String getsinput3() { return sinput3; }

}
