package com.android.luogui.baseproject.XFragment;

import com.android.luogui.baselibrary.base.BaseListFragment;
import com.android.luogui.baselibrary.netWork.retrofit.ApiClint;
import com.android.luogui.baselibrary.netWork.retrofit.HttpParse;
import com.android.luogui.baseproject.ApiService;
import com.android.luogui.baseproject.bean.NewsBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * describe
 * Created by  LuoGui on 2017/11/10.
 */

public class MyFragment extends BaseListFragment<NewsBean> {

    @Override
    protected void setAdapter() {
        initPage = 1;
//        adapter = new SingleAdapter<NewsBean>(getContext(), R.layout.adapter_item_text, mList) {
//            @Override
//            protected void convert(BaseViewHolder holder, NewsBean newsBean, int position) {
//                holder.setText(R.id.tv_title, newsBean.getContent());
//            }
//        };

        adapter = new MutiAdapter(getContext(), mList);



    }

    @Override
    protected void getDataList(int page) {
        Call<String> call = ApiClint.createApiString(ApiService.class).getString(page, "性感");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                List<NewsBean> newsBeanList = HttpParse
                        .parseArrayObject(response.body(), "res", NewsBean.class);
                dispatch(newsBeanList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
