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
