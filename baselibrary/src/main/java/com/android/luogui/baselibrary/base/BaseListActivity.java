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

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;


import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.base.adapter.BaseRecyclerAdapter;
import com.android.luogui.baselibrary.ui.DefineLoadMoreView;
import com.android.luogui.baselibrary.util.DefaultItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public abstract class BaseListActivity<T> extends ToolbarActivity<T> {

    protected SwipeMenuRecyclerView recyclerView;
    protected BaseRecyclerAdapter<T> adapter;
    protected TextView tvEmpty;
    protected int initPage = 0;
    protected int currentPage;
    protected List<T> mList = new ArrayList<>();
    protected PtrClassicFrameLayout ptr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId(), isShowTitle());
        initView();
        setAdapter();
        initPtrLoad();
        setDivider();
        recyclerView.setAdapter(adapter);
        recyclerView.loadMoreFinish(false, true);
        getDataList(initPage);
    }

    /**
     * 自定义布局
     *
     * @return layout_id
     */
    protected int setViewId() {
        return R.layout.activity_base_list;
    }

    protected boolean isShowTitle() {
        return true;
    }

    /**
     * initView
     */
    protected void initView() {
        ptr = (PtrClassicFrameLayout) findViewById(R.id.ptr);
        ptr.disableWhenHorizontalMove(true);
        recyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
    }

    /**
     * init 上下拉
     */
    protected void initPtrLoad() {
        currentPage = initPage;
        recyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getDataList(currentPage);
            }
        });

        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = initPage;
                refreshInit();
                getDataList(currentPage);
            }
        });
    }

    /**
     * 刷新之前可以做的一些操作
     */
    protected void refreshInit() {
    }

    /**
     * 初始化recyclerView
     */
    protected void setDivider() {
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        setLoadMoreBg(loadMoreView);
        recyclerView.addFooterView(loadMoreView); // 添加为Footer。
        recyclerView.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DefaultItemDecoration(this, R.color.gray2_bg, 1, 0));
    }

    /**
     * 设置加载更多 背景
     *
     * @param loadMoreView load
     */
    protected void setLoadMoreBg(DefineLoadMoreView loadMoreView) {

    }

    /**
     * (设置适配器)
     */
    protected abstract void setAdapter();

    /**
     * @param page 获取的页数
     */
    protected abstract void getDataList(int page);


    /**
     * setData
     *
     * @param tempList list
     */
    protected void dispatch(List<T> tempList) {
        if (currentPage == initPage) {
            ptr.refreshComplete();
            adapter.clear();
            adapter.addAll(tempList);
            if (tempList.size() == 0) {
                //无数据
                recyclerView.loadMoreFinish(false, false);
                showEmpty(View.VISIBLE);
            } else {
                //有数据
                recyclerView.loadMoreFinish(false, true);
                showEmpty(View.GONE);
            }
        } else {
            if (tempList.size() != 0) {
                adapter.addAll(tempList);
                recyclerView.loadMoreFinish(false, true);
            } else {
                recyclerView.loadMoreFinish(false, false);
            }
        }
        currentPage++;
    }

    /**
     * showEmpty
     *
     * @param visible visible
     */
    protected void showEmpty(int visible) {
        if (null == tvEmpty) return;
        tvEmpty.setVisibility(visible);
    }

}
