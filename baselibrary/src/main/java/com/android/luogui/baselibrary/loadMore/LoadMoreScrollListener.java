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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    public int advanceItemNum = 4;

    public LoadMoreScrollListener() {
    }

    public LoadMoreScrollListener(int advanceItemNum) {
        this.advanceItemNum = advanceItemNum;
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        checkLoadMoreStatus(recyclerView);
    }

    public void checkLoadMoreStatus(RecyclerView recyclerView) {
        if ((canLoading()) &&
                ((recyclerView.getLayoutManager() instanceof LinearLayoutManager))) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int last = linearLayoutManager.findLastVisibleItemPosition();
            int total = linearLayoutManager.getItemCount();
            if (last >= total - this.advanceItemNum)
                loading();
        }
    }

    public abstract boolean canLoading();

    public abstract void loading();
}