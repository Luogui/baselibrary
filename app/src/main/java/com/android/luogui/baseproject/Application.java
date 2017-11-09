package com.android.luogui.baseproject;

import com.android.luogui.baselibrary.MyApplication;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public class Application extends MyApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        ApiClint.BASE_URL = "http://www.nnajlaw.com:5005/";
    }
}
