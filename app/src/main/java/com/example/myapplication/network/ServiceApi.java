package com.example.myapplication.network;

import com.example.myapplication.data.JoinData;
import com.example.myapplication.data.JoinResponse;
import com.example.myapplication.data.LoginData;
import com.example.myapplication.data.LoginResponse;

import com.example.myapplication.data.CalendarData;
import com.example.myapplication.data.CalendarResponse;
import com.example.myapplication.data.MemoData;
import com.example.myapplication.data.MemoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/st")
    Call<CalendarResponse> userSt(@Body CalendarData data);

    @POST("/user/memo")
    Call<MemoResponse> userMemo(@Body MemoData data);
}
