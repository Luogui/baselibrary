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
    protected BaseRecyclerAdapter adapter;
    protected TextView tvEmpty;
    protected int initPage = 0;
    private int currentPage = initPage;
    protected List<T> mList = new ArrayList<T>();;

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
        recyclerView.refresh();
    }

    protected int getViewId() {
        return R.layout.activity_base_list;
    }

    protected void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.recycler);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
    }

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

    //刷新之前可以做的一些操作
    protected void refreshInit() {
    }

    protected void setDivider() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerView.addItemDecoration(new DefaultItemDecoration(context, R.color.line_color, 1, 0));
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

    protected void showEmpty(int visible){
        tvEmpty.setVisibility(visible);
    }

}
