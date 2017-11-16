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

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract class AbstractLoadViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public AbstractLoadViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public View getItemView() {
        return this.itemView;
    }

    public abstract void loadComplete();

    public abstract void onLoading();

    public abstract void loadFinish();

    public abstract void loadFailure();
}