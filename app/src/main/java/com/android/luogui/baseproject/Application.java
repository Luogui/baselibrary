package com.android.luogui.baseproject;

import com.android.luogui.baselibrary.*;
import com.android.luogui.baselibrary.netWork.retrofit.ApiClint;
import com.android.luogui.baselibrary.util.LogUtil;
import com.arialyy.aria.core.Aria;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public class Application extends MyApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        ApiClint.BASE_URL = "http://localhost/";
        LogUtil.PRINT = BuildConfig.LOG_STATUS;

        Aria.init(this);
    }


}
