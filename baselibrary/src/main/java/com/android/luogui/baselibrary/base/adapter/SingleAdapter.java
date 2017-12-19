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

package com.android.luogui.baselibrary.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;


import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class SingleAdapter<T> extends BaseRecyclerAdapter<T>
{
    protected int mLayoutId;
    protected LayoutInflater mInflater;

    public SingleAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(BaseViewHolder holder, T t, int position)
            {
                SingleAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(BaseViewHolder holder, T t, int position);


}
