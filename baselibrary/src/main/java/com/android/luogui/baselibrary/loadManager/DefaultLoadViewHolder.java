package com.android.luogui.baselibrary.loadManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class DefaultLoadViewHolder extends AbstractLoadViewHolder {

    TextView footHint;

    public DefaultLoadViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.load_more_item, parent, false));
        this.footHint = ((TextView) getItemView().findViewById(R.id.footHint));
    }

    public void loadComplete() {
        this.footHint.setText("上拉加载更多");
    }

    public void onLoading() {
        this.footHint.setText("正在加载中");
    }

    public void loadFinish() {
        this.footHint.setText("全部数据加载完毕");
    }

    public void loadFailure() {
        this.footHint.setText("读取失败,请点击重试");
    }
}