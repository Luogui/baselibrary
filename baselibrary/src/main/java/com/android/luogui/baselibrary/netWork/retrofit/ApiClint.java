/*
 * Copyright (c) 2017.
 * Create by LuoGui.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.luogui.baselibrary.netWork.retrofit;


import android.os.Environment;

import com.android.luogui.baselibrary.BuildConfig;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baselibrary.util.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * describe 网络请求封装
 * Created by  LuoGui on 2017/9/19.
 */

public class ApiClint {

    public static String BASE_URL = BuildConfig.APPLICATION_ID;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){


        //缓存路径和大小
        int DEFAULT_HTTP_CACHE_SIZE = 10 * 1024 * 1024; //缓存大小
        File httpCacheDirectory = new File(Environment.getExternalStorageDirectory(), "HttpCache");
        Cache cache = new Cache(httpCacheDirectory, DEFAULT_HTTP_CACHE_SIZE);



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
                    .cache(cache)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient)
                    .build();

        }
        return retrofit;
    }

    //缓存拦截器，统一缓存60s
    static Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            Response response = chain.proceed(request);

            if (NetworkUtil.isNetworkAvailable()) {
                int maxAge = 60*60*24*2;//缓存失效时间，单位为秒
                return response.newBuilder()
                        .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            }
            return response;
        }
    };
}
