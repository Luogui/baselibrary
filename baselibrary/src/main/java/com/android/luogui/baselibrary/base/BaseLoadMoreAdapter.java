package com.android.luogui.baselibrary.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.luogui.baselibrary.loadManager.SimpleLoadMoreAdapter;
import com.android.luogui.baselibrary.mInterface.OnItemClickListener;

import java.util.Collection;

/**
 * Created by  LuoGui on 2017/4/21.
 */

public abstract class BaseLoadMoreAdapter<T, VH extends RecyclerView.ViewHolder> extends SimpleLoadMoreAdapter<T, VH> {

    public OnItemClickListener<T> itemClick;


    public void setItemClick(OnItemClickListener<T> itemClick) {
        this.itemClick = itemClick;
    }

    public BaseLoadMoreAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
        setInitPage(1);
        setFootShow(false);
    }


    public void add(T item) {
        getList().add(item);
        notifyDataSetChanged();
    }

    public void add(int index, T item) {
        getList().add(index, item);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection != null) {
            getList().addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        getList().clear();
        notifyDataSetChanged();
    }

    public void remove(int position) {
        getList().remove(position);
        notifyDataSetChanged();
    }


    public T getItem(int position) {
        return getList().get(position);
    }

    @Override
    protected void onOtherBindViewHolder(VH vh, final int i, final T t) {
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) itemClick.onItemClick(i, t);
            }
        });
    }

}