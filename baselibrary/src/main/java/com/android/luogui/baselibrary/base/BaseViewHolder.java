package com.android.luogui.baselibrary.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * 描述： baseViewHolder
 * Created by LuoGui on 2017/3/7.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected Context context;


    public BaseViewHolder(Context context, ViewGroup viewGroup, @LayoutRes int layout) {
        super(LayoutInflater.from(context).inflate(layout,viewGroup,false));
        this.context = context;
    }

    public abstract void setValue(T value);



}
