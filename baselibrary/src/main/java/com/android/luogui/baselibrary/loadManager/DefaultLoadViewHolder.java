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

package com.android.luogui.baselibrary.loadManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class DefaultLoadViewHolder extends AbstractLoadViewHolder {

    TextView footHint;

    public DefaultLoadViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.load_more_item, parent, false));
        this.footHint = ((TextView) getItemView().findViewById(R.id.footHint));
    }

    public void loadComplete() {
        this.footHint.setText("上拉加载更多");
    }

    public void onLoading() {
        this.footHint.setText("正在加载中");
    }

    public void loadFinish() {
        this.footHint.setText("全部数据加载完毕");
    }

    public void loadFailure() {
        this.footHint.setText("读取失败,请点击重试");
    }
}