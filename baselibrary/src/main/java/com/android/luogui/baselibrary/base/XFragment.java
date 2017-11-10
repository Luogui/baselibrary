package com.android.luogui.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.luogui.baselibrary.util.DefaultItemDecoration;
import com.android.luogui.baselibrary.xRecyclerView.ProgressStyle;
import com.android.luogui.baselibrary.xRecyclerView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * Created by  LuoGui on 2017/11/10.
 */

public abstract class XFragment<T> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getViewId(), container, false);
        initView(view);
        setAdapter();
        init();
        setDivider();
        recyclerView.setAdapter(adapter);
        firstRequest();
        return view;
    }

    /**
     * 第一次加载数据  是否启用自动刷新
     */
    protected void firstRequest() {
        recyclerView.refresh();
    }


    protected XRecyclerView recyclerView;
    protected BaseRecyclerAdapter<T> adapter;
    protected TextView tvEmpty;
    protected int initPage = 0;
    private int currentPage = initPage;
    protected List<T> mList = new ArrayList<T>();

    /**
     * 自定义布局
     * @return layout_id
     */
    protected int getViewId() {
        return com.android.luogui.baselibrary.R.layout.activity_base_list;
    }

    /**
     * initView
     * @param view
     */
    protected void initView(View view) {
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setArrowImageView(com.android.luogui.baselibrary.R.drawable.iconfont_downgrey);
        recyclerView.addItemDecoration(new DefaultItemDecoration(getContext(), com.android.luogui.baselibrary.R.color.line_color, 1, 0));
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
