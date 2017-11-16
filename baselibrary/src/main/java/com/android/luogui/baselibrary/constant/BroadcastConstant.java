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
