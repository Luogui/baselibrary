package com.android.luogui.baselibrary.loadMore;

import java.util.List;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract interface OnLoadManagerListener<T> {

    public abstract void OnLoadMoreListener();

    public abstract void OnResetLoadListener();

    public abstract void notifyData(List<T> paramList1, List<T> paramList2);
}