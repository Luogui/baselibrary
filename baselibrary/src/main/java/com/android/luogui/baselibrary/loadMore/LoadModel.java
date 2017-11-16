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

package com.android.luogui.baselibrary.loadMore;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class LoadModel<T> implements Loader<T> {

    OnLoadManagerListener<T> onLoadListener;
    LoadStatus loadStatus = LoadStatus.Normal;

    private List<T> list = new ArrayList();
    private int initPage = 0;
    private int page = this.initPage;

    public LoadModel() {
    }

    public LoadModel(List<T> list) {
        this.list = list;
    }

    public void loadComplete(int page, List<T> result, boolean clearOld) {
        if (this.loadStatus == LoadStatus.Loading) {
            this.page = page;

            List oldList = (List) ((ArrayList) this.list).clone();
            if (clearOld) {
                this.list.clear();
            }
            this.list.addAll(result);

            notifyData(oldList, result);
            this.loadStatus = LoadStatus.Normal;
        }
    }

    public void loadClear() {
        this.page = this.initPage;

        notifyData(this.list, new ArrayList());
        this.list.clear();
        this.loadStatus = LoadStatus.Normal;
    }

    public void loadStart() {
        if ((this.loadStatus == LoadStatus.Normal) &&
                (this.onLoadListener != null)) {
            this.loadStatus = LoadStatus.Loading;
            this.onLoadListener.OnLoadMoreListener();
        }
    }

    public void loadComplete() {
        if (this.loadStatus == LoadStatus.Loading)
            this.loadStatus = LoadStatus.Normal;
    }

    public void loadFailure() {
        if (this.loadStatus != LoadStatus.Finish)
            this.loadStatus = LoadStatus.Failure;
    }

    public void loadFinish() {
        this.loadStatus = LoadStatus.Finish;
    }

    public void loadReset() {
        if ((this.loadStatus != LoadStatus.Loading) &&
                (this.onLoadListener != null)) {
            this.loadStatus = LoadStatus.Loading;
            this.onLoadListener.OnResetLoadListener();
        }
    }

    public void notifyData(List<T> oldList, List<T> newList) {
        if (this.onLoadListener != null)
            this.onLoadListener.notifyData(oldList, newList);
    }

    public boolean isLoading() {
        return this.loadStatus == LoadStatus.Loading;
    }

    public void setOnLoadListener(OnLoadManagerListener<T> onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public void setInitPage(int initPage) {
        this.initPage = initPage;
    }

    public int getInitPage() {
        return this.initPage;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page;
    }

    public List<T> getList() {
        return this.list;
    }

    public LoadStatus getLoadStatus() {
        return this.loadStatus;
    }

    public OnLoadManagerListener<T> getOnLoadListener() {
        return this.onLoadListener;
    }

    public static enum LoadStatus {
        Normal(0),
        Loading(1),
        Finish(2),
        Failure(3);

        int value;

        private LoadStatus(int value) {
            this.value = value;
        }
    }
}