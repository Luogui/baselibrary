package com.android.luogui.baseproject;

import com.android.luogui.baselibrary.base.BaseListActivity;
import com.android.luogui.baselibrary.netWork.retrofit.ResultCallBack;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baseproject.adapter.XAdapter;
import com.android.luogui.baseproject.bean.NewsBean;

import retrofit2.Call;

public class XRecycleViewActivity extends BaseListActivity<NewsBean> {


    @Override
    protected void setAdapter() {
        adapter = new XAdapter(this, mList);
        adapter.setItemClick((position, item) -> {
            LogUtil.toast(item.toString());
        });
    }

    @Override
    protected void getDataList(int page) {
        Call<String> call = ApiClint.getApi().getString(page, "");
        call.enqueue(new ResultCallBack<NewsBean>(key) {
            @Override
            public void onSuccess(NewsBean newsBean) {

            }

            @Override
            public void onFailed(int code, String s) {

            }
        });
    }
}
