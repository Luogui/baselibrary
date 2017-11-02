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