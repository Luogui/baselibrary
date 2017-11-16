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
import android.support.v7.widget.RecyclerView;

import com.android.luogui.baselibrary.loadMore.LoadMoreScrollListener;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract class SimpleLoadMoreAdapter<T, TH extends RecyclerView.ViewHolder> extends LoadMoreAdapter<T, TH> {

    public SimpleLoadMoreAdapter(Context context, RecyclerView recyclerView) {
        super(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new LoadMoreScrollListener() {
            public boolean canLoading() {
                return !SimpleLoadMoreAdapter.this.isLoading();
            }

            public void loading() {
                SimpleLoadMoreAdapter.this.loadStart();
            }
        });
    }
}