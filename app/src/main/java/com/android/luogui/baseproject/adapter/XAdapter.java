package com.android.luogui.baseproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.luogui.baselibrary.base.BaseRecyclerAdapter;
import com.android.luogui.baseproject.R;
import com.android.luogui.baseproject.bean.NewsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public class XAdapter extends BaseRecyclerAdapter<NewsBean> {


    public XAdapter(Context context, List<NewsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ViewHolder viewHolder = (ViewHolder) holder;
        NewsBean newsBean = getItem(position);
        viewHolder.tvTitle.setText(newsBean.getContent()==null?position+"" : newsBean.getContent());
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
