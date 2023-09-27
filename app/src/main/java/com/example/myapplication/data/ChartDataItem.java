// ChartDataItem.java

package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class ChartDataItem {
    @SerializedName("month")
    private int month;

    @SerializedName("value2")
    private Float value2;

    // 생성자, getter 및 setter 메서드 등을 포함할 수 있습니다.

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Float getValue2() {
        return value2;
    }

    public void setValue2(Float value2) {
        this.value2 = value2;
    }
}
