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

package com.android.luogui.baselibrary.base.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.luogui.baselibrary.mInterface.OnItemClickListener;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * recycler基础adapter
 * Created by  LuoGui on 2017/4/21.
 */

public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public OnItemClickListener<T> itemClick;
    public Context context;
    protected ItemViewDelegateManager mItemViewDelegateManager;

    public BaseRecyclerAdapter(Context context, List<T> mDataList){
        this.mDataList = mDataList;
        this.context = context;
        mItemViewDelegateManager = new ItemViewDelegateManager();
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
        notifyData();
    }

    public void add(int index, T item) {
        mDataList.add(index, item);
        notifyData();
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection != null) {
            mDataList.addAll(collection);
            notifyData();
        }
    }

    private void notifyData(){
        new Runnable() {
            public void run() {
                notifyItemInserted(getItemCount() - 1);
            }
        }.run();
    }

    public void addAll(T... list) {
        addAll(Arrays.asList(list));
    }

    public void clear() {
        mDataList.clear();
        notifyData();
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyData();
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
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        convert(holder, getItem(position));
    }

    public void convert(BaseViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    /**
     * 根据 itemView 子类的条件获取那种type
     * @param position position
     * @return viewType
     */
    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDataList.get(position), position);
    }

    /**
     * 判断类型是否大于0
     * @return type >0
     */
    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        BaseViewHolder holder = BaseViewHolder.createViewHolder(context, parent, layoutId);
        onViewHolderCreated(holder,holder.getConvertView());
        setListener(parent, holder, viewType);
        return holder;
    }

    public void onViewHolderCreated(BaseViewHolder holder,View itemView){

    }

    protected void setListener(final ViewGroup parent, final BaseViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    int position = viewHolder.getAdapterPosition();
                    itemClick.onItemClick(position , getItem(position));
                }
            }
        });
    }


    /**
     * 固定个类型不能被点击
     * @param viewType
     * @return
     */
    protected boolean isEnabled(int viewType) {
        return true;
    }

    public BaseRecyclerAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public BaseRecyclerAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

}