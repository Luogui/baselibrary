package com.android.luogui.baselibrary.loadManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract class AbstractLoadViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public AbstractLoadViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public View getItemView() {
        return this.itemView;
    }

    public abstract void loadComplete();

    public abstract void onLoading();

    public abstract void loadFinish();

    public abstract void loadFailure();
}