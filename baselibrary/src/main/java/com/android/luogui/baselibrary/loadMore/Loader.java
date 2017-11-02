package com.android.luogui.baselibrary.loadMore;

import java.util.List;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract interface Loader<T> {

    public abstract void loadStart();

    public abstract void loadComplete();

    public abstract void loadFailure();

    public abstract void loadFinish();

    public abstract void loadReset();

    public abstract boolean isLoading();

    public abstract void loadComplete(int paramInt, List<T> paramList, boolean paramBoolean);
}