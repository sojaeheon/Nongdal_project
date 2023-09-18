package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;
// 0807 수정
public class CalendarData {
    @SerializedName("UserId")
    private String UserId;
    @SerializedName("sYear")
    private int sYear;

    @SerializedName("sMonth")
    private int sMonth;

    @SerializedName("sDate")
    private int sDate;

    public CalendarData(String UserId, int sYear, int sMonth, int sDate) {
        this.UserId = UserId;
        this.sYear = sYear;
        this.sMonth = sMonth;
        this.sDate = sDate;
    }
}
