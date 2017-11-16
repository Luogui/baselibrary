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
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class RecyclerScrollView extends NestedScrollView {

    private int downY;
    private int mTouchSlop = 0;
    NeedMoreRequest needMoreRequest;
    OnScrollChangeListener onScrollChangeListener;

    public RecyclerScrollView(Context context) {
        super(context);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    public RecyclerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        init();
    }

    public static void configRecyclerView(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void init() {
        super.setOnScrollChangeListener(new OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (RecyclerScrollView.this.onScrollChangeListener != null) {
                    RecyclerScrollView.this.onScrollChangeListener.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY);
                }
                int sumheight = v.getChildAt(0).getHeight();
                int height = v.getHeight();
                if ((scrollY + height > sumheight - 900) &&
                        (RecyclerScrollView.this.needMoreRequest != null))
                    RecyclerScrollView.this.needMoreRequest.request();
            }
        });
    }

    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        this.onScrollChangeListener = l;
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case 0:
                this.downY = (int) e.getRawY();
                break;
            case 2:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - this.downY) <= this.mTouchSlop) break;
                return true;
        }

        return super.onInterceptTouchEvent(e);
    }

    public void setNeedMoreRequest(NeedMoreRequest needMoreRequest) {
        this.needMoreRequest = needMoreRequest;
    }

    public static abstract interface NeedMoreRequest {
        public abstract void request();
    }
}