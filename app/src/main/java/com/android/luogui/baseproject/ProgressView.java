/*
 * Copyright (c) 2021.
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

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;



public class ProgressView extends View {

    protected int mHeight;
    protected int mWidth;
    protected Context context;
    protected Paint paint;
    protected int centerX, centerY;


    /**
     * 进度
     */
    private int progress = 0;

    public ProgressView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    protected void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

    }

    public void setProgress(int progress){
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        this.mHeight = h;
        this.mWidth = w;
        centerX = w / 2;
        centerY = h / 2;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX, centerY);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setColor(ContextCompat.getColor(context, R.color.mask_progress_bg));
        paint.setStrokeWidth(mHeight * 0.8f);
        canvas.drawLine(-mWidth / 2f *0.95f , 0, mWidth / 2f*0.95f, 0, paint);
        paint.setColor(ContextCompat.getColor(context, R.color.mask_progress));
        paint.setStrokeWidth(mHeight * 0.75f);
        canvas.drawLine(-mWidth / 2f *0.95f* 0.9999f, 0,
                -mWidth / 2f *0.95f + mWidth *0.95f * 0.9999f  * progress /100f , 0, paint);
    }

}
