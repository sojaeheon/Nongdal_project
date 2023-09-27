package com.example.myapplication.data;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChartDataResponse {
    @SerializedName("data")
    private List<ChartDataItem> data;

    public List<ChartDataItem> getData() {
        return data;
    }

    public void setData(List<ChartDataItem> data) {
        this.data = data;
    }
}
