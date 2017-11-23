package com.android.luogui.baseproject;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public class ApiClint extends com.android.luogui.baselibrary.netWork.retrofit.ApiClint {


    public static ApiService getApi(){
        return getRetrofit().create(ApiService.class);
    }

    @Override
    public String getBaseUrl() {
        return null;
    }
}
