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

package com.android.luogui.baselibrary.LayerAdapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * describe
 * Created by LuoGui on 2017/4/10.
 */

public class LayerItemDecoration extends RecyclerView.ItemDecoration {

    int groupTopDistance;
    int groupBottomDistance;
    int childDistance;
    Paint paint;

    public LayerItemDecoration(Context context, @ColorRes int color, int groupTopDistance, int groupBottomDistance, int childDistance) {
        this.groupTopDistance = groupTopDistance;
        this.groupBottomDistance = groupBottomDistance;
        this.childDistance = childDistance;

        paint = new Paint();
        paint.setColor(context.getResources().getColor(color));
        paint.setStyle ( Paint.Style.STROKE ) ;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        LayerAdapter layerAdapter = (LayerAdapter) parent.getAdapter();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            int viewType = layerAdapter.getItemViewType(i);
            paint.setStrokeWidth(getHeight(viewType));
            Path path = new Path();
            path.moveTo(left,top+getHeight(viewType)/2);
            path.lineTo(right,top+getHeight(viewType)/2);
            c.drawPath(path,paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int positon = parent.getChildAdapterPosition(view);
        if (parent.getAdapter() instanceof LayerAdapter){
            LayerAdapter layerAdapter = (LayerAdapter) parent.getAdapter();
            int viewType = layerAdapter.getItemViewType(positon);
            outRect.set(0,0,0,getHeight(viewType));


        }
    }

    private int getHeight(int viewType){
        if (viewType==0){
            return groupTopDistance;
        }else if (viewType==-1){
            return groupBottomDistance;
        }else {
            return childDistance;
        }
    }
}
