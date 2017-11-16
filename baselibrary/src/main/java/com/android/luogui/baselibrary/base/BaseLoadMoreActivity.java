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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.loadMore.OnLoadManagerListener;
import com.android.luogui.baselibrary.util.DefaultItemDecoration;

import java.util.List;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 描述：上下拉activity
 * Created by LuoGui on 2017/1/7.
 */

public abstract class BaseLoadMoreActivity<T> extends ToolbarActivity<T> {

    protected RecyclerView recyclerView;
    protected PtrClassicFrameLayout ptrClassicFrameLayout;
    protected BaseLoadMoreAdapter adapter;
    protected TextView tvEmpty;

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
        adapter.loadReset();
    }

    protected int getViewId() {
        return R.layout.activity_base_list2;
    }

    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.ptr);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
    }

    protected void init() {
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshInit();
                adapter.loadReset();
            }
        });



        adapter.setOnLoadListener(new OnLoadManagerListener() {
            @Override
            public void OnLoadMoreListener() {
                getDataList(adapter.getPage());
            }

            @Override
            public void OnResetLoadListener() {
                getDataList(adapter.getInitPage());
            }

            @Override
            public void notifyData(List list, List list1) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    protected void refreshInit() {
    }

    protected void setDivider() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DefaultItemDecoration(context, R.color.transparent, 3, 0));
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


    protected void dispatch(T temp, int page) {
        List tempList = (List) temp;
        if (page == 0 && tempList.size() == 0) {
            showEmpty(View.VISIBLE);
        } else {
            showEmpty(View.GONE);
        }

        if (tempList.size() == 0) {
            adapter.loadFinish();
            return;
        }
        if (page == adapter.getInitPage()) {
            adapter.loadEndReset(page + 1, tempList);
        } else {
            adapter.loadEndMore(page + 1, tempList);
        }
    }

    protected void showEmpty(int visible) {
        if (tvEmpty == null) return;
        tvEmpty.setVisibility(visible);
    }
}
