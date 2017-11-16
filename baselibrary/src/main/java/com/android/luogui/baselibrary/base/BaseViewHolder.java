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

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * 描述： baseViewHolder
 * Created by LuoGui on 2017/3/7.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected Context context;


    public BaseViewHolder(Context context, ViewGroup viewGroup, @LayoutRes int layout) {
        super(LayoutInflater.from(context).inflate(layout,viewGroup,false));
        this.context = context;
    }

    public abstract void setValue(T value);



}
