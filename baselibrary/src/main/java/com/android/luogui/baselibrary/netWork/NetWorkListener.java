package com.android.luogui.baselibrary.netWork;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract interface NetWorkListener {

    public abstract void start(String paramString);

    public abstract void endOnSuccess(String paramString1, String paramString2);

    public abstract void endOnFaile(String paramString1, String paramString2);
}