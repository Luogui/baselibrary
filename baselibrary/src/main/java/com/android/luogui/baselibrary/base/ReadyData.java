package com.android.luogui.baselibrary.base;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract interface ReadyData
{
    public abstract void onReadyFinish();

    public abstract void setReady(boolean paramBoolean);

    public abstract boolean isReady();
}