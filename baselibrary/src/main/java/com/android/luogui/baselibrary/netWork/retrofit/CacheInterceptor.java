/*
 * Copyright (c) 2018.
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



import com.android.luogui.baselibrary.MyApplication;
import com.android.luogui.baselibrary.util.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.android.luogui.baselibrary.util.HttpUtils.getUserAgent;


/**
 * Created by Horrarndoo on 2017/9/12.
 * <p>
 * CacheInterceptor
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtil.isNetworkConnected(MyApplication.getInstance())) {
            // 有网络时, 缓存1小时
            int maxAge = 60 * 60;
            request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .header("User-Agent", getUserAgent())
                    .build();

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            // 无网络时，缓存为4周
            int maxStale = 60 * 60 * 24 * 28;
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .removeHeader("User-Agent")
                    .header("User-Agent", getUserAgent())
                    .build();

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }

        //        Request request = chain.request();
        //        if (!NetworkConnectionUtils.isConnected(AppUtils.getContext())) {
        //            request = request.newBuilder()
        //                    .cacheControl(CacheControl.FORCE_CACHE)
        //                    .build();
        //        }
        //        Response response = chain.proceed(request);
        //        if (NetworkConnectionUtils.isConnected(AppUtils.getContext())) {
        //            int maxAge = 0;
        //            // 有网络时, 不缓存, 最大保存时长为0
        //            response.newBuilder()
        //                    .header("Cache-Control", "public, max-age=" + maxAge)
        //                    .removeHeader("Pragma")
        //                    .build();
        //        } else {
        //            // 无网络时，设置超时为4周
        //            int maxStale = 60 * 60 * 24 * 28;
        //            response.newBuilder()
        //                    .header("Cache-Control", "public, only-if-cached, max-stale=" +
        // maxStale)
        //                    .removeHeader("Pragma")
        //                    .build();
        //        }
        //        return response;
    }
}
