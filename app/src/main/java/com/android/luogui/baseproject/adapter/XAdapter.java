package com.android.luogui.baseproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.android.luogui.baselibrary.base.BaseRecyclerAdapter;
import com.android.luogui.baseproject.bean.NewsBean;

import java.util.List;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public class XAdapter extends BaseRecyclerAdapter<NewsBean> {

    private Context context;


    public XAdapter(Context context, List<NewsBean> newsBeanList) {
        this.context = context;
        this.mDataList = newsBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }
}
