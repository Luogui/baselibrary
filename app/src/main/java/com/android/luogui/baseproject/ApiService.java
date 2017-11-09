package com.android.luogui.baseproject;


import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public interface ApiService {

    @POST("user/home")
    Call<String>  getString(@Query("page") int page, @Query("uid") String uid);
}
