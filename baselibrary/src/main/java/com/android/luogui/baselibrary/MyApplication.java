package com.android.luogui.baselibrary;

import android.app.Application;
import android.content.Context;

/**
 * application
 * Created by LuoGui on 2016/9/11.
 */
public class MyApplication extends Application{

    private static MyApplication instance;


    public static MyApplication getInstance(){
        return instance;
    }

    private static Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
