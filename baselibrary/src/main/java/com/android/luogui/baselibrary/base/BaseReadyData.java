package com.android.luogui.baselibrary.base;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class BaseReadyData implements ReadyData {

    private boolean isReady = false;

    public void onReadyFinish() {
    }

    public void setReady(boolean ready) {
        this.isReady = ready;
        if (this.isReady)
            onReadyFinish();
    }

    public boolean isReady() {
        return this.isReady;
    }
}