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

package com.android.luogui.baseproject;


import com.android.luogui.baselibrary.base.BaseListActivity;
import com.android.luogui.baselibrary.base.BaseSlideListActivity;
import com.android.luogui.baselibrary.base.adapter.BaseViewHolder;
import com.android.luogui.baselibrary.base.adapter.SingleAdapter;
import com.android.luogui.baselibrary.netWork.retrofit.HttpParse;
import com.android.luogui.baseproject.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatchRecordActivity extends BaseListActivity<NewsBean> {


    @Override
    protected void setAdapter() {
        initPage = 1;
//        setIcon(R.drawable.icon_back);
//        setTitle("抓取记录");
        adapter = new SingleAdapter<NewsBean>(context, R.layout.adapter_item_catch, mList) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, NewsBean catchRecordBean, int i) {

            }
        };
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    protected void getDataList(int i) {
        Call<String> call = ApiClint.getApi().getString(i, "性感");
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
