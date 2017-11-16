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

package com.android.luogui.baselibrary.loadManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;


import com.android.luogui.baselibrary.loadMore.LoadModel;
import com.android.luogui.baselibrary.loadMore.Loader;
import com.android.luogui.baselibrary.loadMore.OnLoadManagerListener;

import java.util.ArrayList;
import java.util.List;

public abstract class LoadMoreAdapter<T, TH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter implements Loader<T> {

    private Context context;
    private boolean isFootShow = false;
    protected LoadModel<T> loadModel;

    public LoadMoreAdapter(Context context) {
        this(context, new ArrayList());
    }

    public LoadMoreAdapter(Context context, List<T> list) {
        this.context = context;
        this.loadModel = new LoadModel(list);
    }

    public T getItem(int position) {
        return this.loadModel.getList().get(position);
    }

    public int getItemViewType(int position) {
        if (position >= this.loadModel.getList().size()) {
            return 9981;
        }
        return getOtherItemViewType(position);
    }

    protected int getOtherItemViewType(int position) {
        return 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 9981) {
            return getFootViewHolder(parent);
        }
        return onOtherCreateViewHolder(parent, viewType);
    }

    @NonNull
    protected AbstractLoadViewHolder getFootViewHolder(ViewGroup parent) {
        return new DefaultLoadViewHolder(this.context, parent);
    }

    protected abstract TH onOtherCreateViewHolder(ViewGroup paramViewGroup, int paramInt);

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof AbstractLoadViewHolder) {
            AbstractLoadViewHolder loadViewHolder = (AbstractLoadViewHolder) holder;
            switch (this.loadModel.getLoadStatus().ordinal()) {
                case 1:
                    loadViewHolder.loadComplete();
                    break;
                case 2:
                    loadViewHolder.onLoading();
                    break;
                case 3:
                    loadViewHolder.loadFinish();
                    break;
                case 4:
                    loadViewHolder.loadFailure();
            }
        } else {
            this.onOtherBindViewHolder((TH) holder, position, this.getItem(position));
        }

    }

    protected abstract void onOtherBindViewHolder(TH paramTH, int paramInt, T paramT);

    public int getItemCount() {
        if (this.isFootShow) {
            return this.loadModel.getList().size() == 0 ? 0 : this.loadModel.getList().size() + 1;
        }
        return this.loadModel.getList().size();
    }

    public Context getContext() {
        return this.context;
    }

    public void setFootShow(boolean footShow) {
        this.isFootShow = footShow;
    }

    public void loadEndMore(int page, List<T> result) {
        this.loadModel.loadComplete(page, result, false);
    }

    public void loadEndReset(int page, List<T> result) {
        this.loadModel.loadComplete(page, result, true);
    }

    public void loadComplete(int page, List<T> result, boolean clearOld) {
        this.loadModel.loadComplete(page, result, clearOld);
    }

    public void loadClear() {
        this.loadModel.loadClear();
    }

    public void loadStart() {
        this.loadModel.loadStart();
    }

    public void loadComplete() {
        this.loadModel.loadComplete();
    }

    public void loadFailure() {
        this.loadModel.loadFailure();
    }

    public void loadFinish() {
        this.loadModel.loadFinish();
    }

    public void loadReset() {
        this.loadModel.loadReset();
    }

    public boolean isLoading() {
        return this.loadModel.isLoading();
    }

    public void setOnLoadListener(OnLoadManagerListener onLoadListener) {
        this.loadModel.setOnLoadListener(onLoadListener);
    }

    public void setInitPage(int initPage) {
        this.loadModel.setInitPage(initPage);
    }

    public int getInitPage() {
        return this.loadModel.getInitPage();
    }

    public void setPage(int page) {
        this.loadModel.setPage(page);
    }

    public int getPage() {
        return this.loadModel.getPage();
    }

    public List<T> getList() {
        return this.loadModel.getList();
    }

}