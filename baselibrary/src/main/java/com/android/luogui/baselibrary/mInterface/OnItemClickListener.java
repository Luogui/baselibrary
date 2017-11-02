package com.android.luogui.baselibrary.mInterface;


/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract interface OnItemClickListener<T> {

    public abstract void onItemClick(int position, T item);

}