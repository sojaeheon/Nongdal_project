package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class DeleteData {
    @SerializedName("sYear")
    private int sYear;

    @SerializedName("sMonth")
    private int sMonth;

    @SerializedName("sDate")
    private int sDate;

    @SerializedName("sText")
    private String sText;

    @SerializedName("sinput1")
    private String sinput1;

    @SerializedName("sinput2")
    private String sinput2;

    @SerializedName("sinput3")
    private String sinput3;



    public DeleteData(int sYear, int sMonth, int sDate, String sinput1, String sinput2, String sinput3, String sText) {
        this.sYear = sYear;
        this.sMonth = sMonth;
        this.sDate = sDate;
        this.sinput1 = sinput1;
        this.sinput2 = sinput2;
        this.sinput3 = sinput3;
        this.sText = sText;
    }
}
