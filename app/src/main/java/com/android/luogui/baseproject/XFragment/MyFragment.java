package com.android.luogui.baseproject.XFragment;

import com.android.luogui.baselibrary.netWork.retrofit.ResultCallBack;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baseproject.ApiClint;
import com.android.luogui.baseproject.adapter.XAdapter;
import com.android.luogui.baseproject.bean.NewsBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * describe
 * Created by  LuoGui on 2017/11/10.
 */

public class MyFragment extends XFragment<NewsBean> {

    @Override
    protected void setAdapter() {
        adapter = new XAdapter(getContext(), mList);
        adapter.setItemClick((position, item) -> {
            LogUtil.toast(item.toString());
        });
    }

    @Override
    protected void getDataList(int page) {
        Call<String> call = ApiClint.getApi().getString(page, "");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
