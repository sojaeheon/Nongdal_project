package com.example.myapplication.network;

import com.example.myapplication.data.JoinData;
import com.example.myapplication.data.JoinResponse;
import com.example.myapplication.data.LoginData;
import com.example.myapplication.data.LoginResponse;
import com.example.myapplication.data.CheckIdData;
import com.example.myapplication.data.CheckIdResponse;

import com.example.myapplication.data.CalendarData;
import com.example.myapplication.data.CalendarResponse;
import com.example.myapplication.data.MemoData;
import com.example.myapplication.data.DeleteData;
import com.example.myapplication.data.MemoResponse;

import com.example.myapplication.data.mypage.ChangeEmailData;
import com.example.myapplication.data.mypage.ChangeEmailResponse;
import com.example.myapplication.data.mypage.ChangePwData;
import com.example.myapplication.data.mypage.ChangePwResponse;

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

    @POST("/user/ChangeE")
    Call<ChangeEmailResponse> userChangeE(@Body ChangeEmailData data);

    @POST("/user/ChangeP")
    Call<ChangePwResponse> userChangeP(@Body ChangePwData data);

    @POST("/user/CheckId")
    Call<CheckIdResponse> userCheckId(@Body CheckIdData data);

    @POST("/user/delete")
    Call<CalendarResponse> userDelete(@Body DeleteData data);
}
