package com.android.luogui.baseproject;


import com.android.luogui.baseproject.bean.BuyInHouseBean;
import com.android.luogui.baseproject.bean.NewsBean;
import com.android.luogui.baseproject.bean.NewsBean2;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public interface ApiService {


    String header = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleH" +
            "AiOjE2Mzg5NTUyOTUsInVzZXJuYW1lIjoiYW" +
            "RtaW4ifQ.TM7NvjsLo9pZRv-YgvhB74Yphu-shGlhT_VV9EDgWfY";

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

    /**
     * 上传头像
     */
    @Multipart
    @POST("user/header")
    Call<String> upHeader(@Query("uid") String uid, @Part MultipartBody.Part requestBody);
    /**
     * 上传头像
     */
    @GET("api/phone/home")
    Call<String> getHome();

    @FormUrlEncoded
    @POST("area/addArea")
    Observable<String>  addArea(@Field("areaName") String areaName,
                          @Field("areaName") int areaSex,
                          @Field("areaName") int num,
                          @Field("areaDesc") String areaDesc);


    /**
     * 采购入库
     * @return
     */
    @POST("business/invMisc/add")
    Call<String> buyInHouse(@Header("X-Access-Token") String header,
                            @Body BuyInHouseBean buyInHouseBean);
}
