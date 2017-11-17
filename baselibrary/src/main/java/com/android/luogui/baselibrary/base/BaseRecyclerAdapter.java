/*
 * Copyright (c) 2017.
 * Create by LuoGui.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.luogui.baselibrary.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.luogui.baselibrary.mInterface.OnItemClickListener;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * recycler基础adapter
 * Created by  LuoGui on 2017/4/21.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {

    public OnItemClickListener<T> itemClick;
    public Context context;

    public BaseRecyclerAdapter(Context context, List<T> mDataList){
        this.mDataList = mDataList;
        this.context = context;
    }

    public void setItemClick(OnItemClickListener<T> itemClick) {
        this.itemClick = itemClick;
    }

    public List<T> mDataList;

    public void setDataList(List<T> list) {
        this.mDataList = list;
    }

    public void add(T item) {
        mDataList.add(item);
        notifyDataSetChanged();
    }

    public void add(int index, T item) {
        mDataList.add(index, item);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection != null) {
            mDataList.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(T... list) {
        addAll(Arrays.asList(list));
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mDataList==null?0:mDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return mDataList.get(position).hashCode();
    }

    public T getItem(int position) {
        return mDataList.get(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick!=null) itemClick.onItemClick(position , getItem(position));
            }
        });
    }

}