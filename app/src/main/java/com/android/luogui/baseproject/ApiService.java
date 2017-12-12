package com.android.luogui.baseproject;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("user/figure")
    Call<String>  getString(@Field("page") int page, @Field("type") String type);
}
