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

package com.android.luogui.baseproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.luogui.baselibrary.base.adapter.BaseRecyclerAdapter;
import com.android.luogui.baselibrary.base.adapter.BaseViewHolder;
import com.android.luogui.baseproject.R;
import com.android.luogui.baseproject.bean.NewsBean;

import java.util.List;

/**
 * describe
 * Created by  LuoGui on 2017/12/20.
 */

public class TestMultiAdapter extends BaseRecyclerAdapter<NewsBean> {


    public TestMultiAdapter(Context context, List<NewsBean> mDataList) {
        super(context, mDataList);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_text, parent, false);
            return new ImageViewHolder(context, view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_text, parent, false);
            return new ImageViewHolder(context, view);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position%2==0) return 0;
        return 1;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        String content = getItem(position).getContent();
        NewsBean newsBean = getItem(position);
        if (getItemViewType(position)==0){
            newsBean.setContent("4" + content);
        }else {
            newsBean.setContent("5" + content);
        }
        ImageViewHolder viewHolder = (ImageViewHolder) holder;
        viewHolder.setValue(newsBean);
    }
}
