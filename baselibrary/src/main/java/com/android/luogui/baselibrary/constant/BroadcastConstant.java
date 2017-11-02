package com.android.luogui.baselibrary.constant;


import com.android.luogui.baselibrary.MyApplication;

/**
 * 广播常量
 * Created by LuoGui on 2017/2/21.
 */

public class BroadcastConstant {

    //activity 关闭
    public static final String ACTIVITY_FINISH = MyApplication.getInstance().getPackageName() + "_activity_finish";
    //登录成功
    public static final String LOGIN_SUCCESS = MyApplication.getInstance().getPackageName() +"_login_success";
    //退出登录
    public static final String LOGIN_EXITS = MyApplication.getInstance().getPackageName() +"_exits_success";
    //第三方登录
    public static final String LOGIN_QQ_WX = MyApplication.getInstance().getPackageName() +"_qq_wx_login";


}
