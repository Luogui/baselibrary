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