package com.example.myapplication.network;

import com.example.myapplication.data.startpage.JoinData;
import com.example.myapplication.data.startpage.JoinResponse;
import com.example.myapplication.data.startpage.LoginData;
import com.example.myapplication.data.startpage.LoginResponse;
import com.example.myapplication.data.startpage.CheckIdData;
import com.example.myapplication.data.startpage.CheckIdResponse;
import com.example.myapplication.data.startpage.FindIdData;
import com.example.myapplication.data.startpage.FindIdResponse;
import com.example.myapplication.data.startpage.FindPwData;
import com.example.myapplication.data.startpage.FindPwResponse;

import com.example.myapplication.data.CalendarData;
import com.example.myapplication.data.CalendarResponse;
import com.example.myapplication.data.MemoData;
import com.example.myapplication.data.DeleteData;
import com.example.myapplication.data.MemoResponse;
import com.example.myapplication.data.ChartDataItem;
import com.example.myapplication.data.ChartDataResponse;

import com.example.myapplication.data.mypage.ChangeEmailData;
import com.example.myapplication.data.mypage.ChangeEmailResponse;
import com.example.myapplication.data.mypage.ChangePwData;
import com.example.myapplication.data.mypage.ChangePwResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

// 0807 수정

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/st")
    Call<CalendarResponse> userSt(@Body CalendarData data);

    @POST("/user/memo")
    Call<MemoResponse> userMemo(@Body MemoData data);

    @POST("/user/changeE")
    Call<ChangeEmailResponse> userChangeE(@Body ChangeEmailData data);

    @POST("/user/changeP")
    Call<ChangePwResponse> userChangeP(@Body ChangePwData data);

    @POST("/user/checkId")
    Call<CheckIdResponse> userCheckId(@Body CheckIdData data);

    @POST("/user/delete")
    Call<CalendarResponse> userDelete(@Body DeleteData data);

    @POST("/user/FindI")
    Call<FindIdResponse> userFindI(@Body FindIdData data);

    @POST("/user/FindP")
    Call<FindPwResponse> userFindP(@Body FindPwData data);

    @POST("/user/prediction/{userId}")
    Call<ChartDataResponse> userPredict(@Path("userId") String userId);

}
