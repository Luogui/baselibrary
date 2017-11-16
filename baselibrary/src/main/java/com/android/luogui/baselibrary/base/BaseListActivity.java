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
import com.android.luogui.baselibrary.util.DefaultItemDecoration;
import com.android.luogui.baselibrary.xRecyclerView.ProgressStyle;
import com.android.luogui.baselibrary.xRecyclerView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListActivity<T> extends ToolbarActivity<T> {

    protected XRecyclerView recyclerView;
    protected BaseRecyclerAdapter<T> adapter;
    protected TextView tvEmpty;
    protected int initPage = 0;
    private int currentPage = initPage;
    protected List<T> mList = new ArrayList<T>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        setToolbarBackBtn();
        initView();
        setAdapter();
        init();
        setDivider();
        recyclerView.setAdapter(adapter);
        firstRequest();
    }

    /**
     * 第一次加载数据  是否启用自动刷新
     */
    protected void firstRequest() {
        recyclerView.refresh();
    }

    /**
     * 自定义布局
     * @return layout_id
     */
    protected int getViewId() {
        return com.android.luogui.baselibrary.R.layout.activity_base_list;
    }

    /**
     * initView
     */
    protected void initView() {
        recyclerView = (XRecyclerView) view.findViewById(com.android.luogui.baselibrary.R.id.recycler);
        tvEmpty = (TextView) view.findViewById(com.android.luogui.baselibrary.R.id.tv_empty);
    }

    /**
     * init 上下拉
     */
    protected void init() {

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                refreshInit();
                getDataList(currentPage);
            }

            @Override
            public void onLoadMore() {
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setArrowImageView(com.android.luogui.baselibrary.R.drawable.iconfont_downgrey);
        recyclerView.addItemDecoration(new DefaultItemDecoration(this, com.android.luogui.baselibrary.R.color.line_color, 1, 0));
    }

    /**
     * @return void
     * @Title: setAdapter
     * @Description: (设置适配器)
     */
    protected abstract void setAdapter();

    /**
     * @return List<T>
     * @Title: getDataList
     * @Description: (获取数据列表)
     */
    protected abstract void getDataList(int page);


    /**
     * setData
     * @param tempList list
     */
    protected void dispatch(List<T> tempList) {
        if (currentPage==initPage) {
            recyclerView.refreshComplete();
            adapter.clear();
            adapter.addAll(tempList);
            if (tempList.size()==0){
                //无数据
                showEmpty(View.VISIBLE);
            }else {
                //有数据
                showEmpty(View.GONE);
            }
        }else {
            if (tempList.size()!=0) {
                adapter.addAll(tempList);
                recyclerView.loadMoreComplete();
            }else {
                recyclerView.setNoMore(true);
            }
        }
        currentPage ++;
    }

    /**
     * showEmpty
     * @param visible visible
     */
    protected void showEmpty(int visible){
        tvEmpty.setVisibility(visible);
    }

}
