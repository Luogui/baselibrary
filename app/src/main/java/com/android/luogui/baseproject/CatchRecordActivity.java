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


import com.android.luogui.baselibrary.base.BaseSlideListActivity;
import com.android.luogui.baselibrary.base.adapter.BaseViewHolder;
import com.android.luogui.baselibrary.base.adapter.SingleAdapter;
import com.android.luogui.baselibrary.netWork.retrofit.ApiClint;
import com.android.luogui.baseproject.bean.NewsBean2;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CatchRecordActivity extends BaseSlideListActivity<NewsBean2.ResBean> {


    @Override
    protected void setAdapter() {
        initPage = 1;
        setIcon(R.drawable.icon_back);
        setTitle("抓取记录");
        adapter = new SingleAdapter<NewsBean2.ResBean>(context, R.layout.adapter_item_text, mList) {
            @Override
            protected void convert(BaseViewHolder holder, NewsBean2.ResBean res, int position) {
                holder.setText(R.id.tv_title, res.getContent());
            }
        };
    }


    @Override
    protected void getDataList(int i) {
        ApiClint.createApi(ApiService.class)
                .getString2(i, "性感")
                .map(newsBean2 -> {
                    if (newsBean2.getStatus().equals("success")) {
                        return newsBean2.getRes();
                    }
                    return new ArrayList<NewsBean2.ResBean>();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resBeen -> dispatch(resBeen));
    }
}
