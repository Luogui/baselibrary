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

package com.android.luogui.baseproject.XFragment;

import com.android.luogui.baselibrary.base.adapter.BaseViewHolder;
import com.android.luogui.baselibrary.base.adapter.ItemViewDelegate;
import com.android.luogui.baseproject.R;
import com.android.luogui.baseproject.bean.NewsBean;

/**
 * describe
 * Created by  LuoGui on 2017/12/7.
 */

public class SingleItemView implements ItemViewDelegate<NewsBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.adapter_item_text;
    }

    @Override
    public boolean isForViewType(NewsBean item, int position) {
        return position%2==0;
    }

    @Override
    public void convert(BaseViewHolder holder, NewsBean newsBean, int position) {
        holder.setText(R.id.tv_title, position%2 + newsBean.getContent());
    }
}
