package com.android.luogui.baselibrary.netWork.retrofit;

import com.android.luogui.baselibrary.BuildConfig;
import com.android.luogui.baselibrary.util.LogUtil;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * describe 网络请求封装
 * Created by  LuoGui on 2017/9/19.
 */

public class ApiClint {

    public static String BASE_URL = BuildConfig.APPLICATION_ID;

    private static Retrofit retrofit;


    protected static Retrofit getRetrofit(){
        if (retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    LogUtil.i(message);
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

}
