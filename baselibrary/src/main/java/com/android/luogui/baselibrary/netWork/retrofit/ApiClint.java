package com.android.luogui.baselibrary.netWork.retrofit;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * describe 网络请求封装
 * Created by  LuoGui on 2017/9/19.
 */

public class ApiClint {

    public static final String BASE_URL = "http://192.168.4.152:8090/WebDemo/";

    private static Retrofit retrofit;


    private static Retrofit getRetrofit(){
        if (retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("Log", message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient)
                    .build();
        }

        return retrofit;
    }

    public static ApiService getApi(){
        return getRetrofit().create(ApiService.class);
    }

}
