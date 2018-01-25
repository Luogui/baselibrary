package com.android.luogui.baseproject;


import com.android.luogui.baseproject.bean.NewsBean;
import com.android.luogui.baseproject.bean.NewsBean2;


import io.reactivex.Observable;
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

    @FormUrlEncoded
    @POST("user/figure")
    Observable<NewsBean2> getString2(@Field("page") int page, @Field("type") String type);

    @FormUrlEncoded
    @POST("user/figure")
    Observable<NewsBean2> getString3(@Field("page") int page, @Field("type") String type);

    /**
     * 获取发现页面数据
     */
    @FormUrlEncoded
    @POST("user/figure")
    Call<String> getFoundData(@Field("page") int page, @Field("type") String type);
}
