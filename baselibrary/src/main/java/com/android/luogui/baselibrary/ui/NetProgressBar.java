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

package com.android.luogui.baselibrary.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.android.luogui.baselibrary.constant.Constant;
import com.android.luogui.baselibrary.mInterface.OnFinishResultListener;
import com.android.luogui.baselibrary.util.LogUtil;


/**
 * 描述：加载中...
 * Created by LuoGui on 2017/8/28.
 */

public class NetProgressBar extends View
{
    Paint backPaint;
    Paint frontPaint;
    int strokeWidth = 16;
    int bgColor = 0;
    int normalColor = ContextCompat.getColor(getContext(), Constant.PROGRESS_BAR_COLOR);
    int successColor = -16711936;
    int failedColor = -65536;
    int progressMinAngle = 30;
    int progressMaxAngle = 270;
    int stepLength = 16;
    int variateLength = 3;
    OnFinishResultListener finishResultListener;
    Status status = Status.Normal;

    int angle = 0;
    int varStatus = 0;
    int paintColor = this.normalColor;
    int progressAngle = 30;
    float shadePercent = 0.0F;
    int startShadeLength = this.progressMaxAngle;

    boolean drawing = true;

    private Runnable runnable = new Runnable()
    {
        public void run() {
            while (NetProgressBar.this.drawing)
                try {
                    NetProgressBar.this.angle = (NetProgressBar.this.angle >= 360 ? 0 : NetProgressBar.this.angle + NetProgressBar.this.stepLength);
                    if (NetProgressBar.this.progressAngle >= NetProgressBar.this.progressMaxAngle)
                        NetProgressBar.this.varStatus = 2;
                    else if (NetProgressBar.this.progressAngle <= NetProgressBar.this.progressMinAngle) {
                        NetProgressBar.this.varStatus = 1;
                    }
                    if ((NetProgressBar.this.status != Status.Normal) && (NetProgressBar.this.varStatus != 3))
                    {
                        NetProgressBar.this.varStatus = 3;
                        NetProgressBar.this.startShadeLength = NetProgressBar.this.progressAngle;
                    }

                    if (NetProgressBar.this.varStatus == 3) {
                        NetProgressBar.this.paintColor = NetProgressBar.this.getShadeColor(NetProgressBar.this.shadePercent, NetProgressBar.this.status == Status.Success ? NetProgressBar.this.successColor : NetProgressBar.this.failedColor);
                        int num = (360 - NetProgressBar.this.startShadeLength) / NetProgressBar.this.variateLength / 2;
                        NetProgressBar.this.shadePercent = (num == 0 ? 1.0F : (float)(NetProgressBar.this.shadePercent + 1.0D / num));
                    }

                    switch (NetProgressBar.this.varStatus) {
                        case 1:
                            NetProgressBar.this.progressAngle += NetProgressBar.this.variateLength;
                            break;
                        case 2:
                            NetProgressBar.this.progressAngle -= NetProgressBar.this.variateLength;
                            break;
                        case 3:
                            NetProgressBar.this.progressAngle += NetProgressBar.this.variateLength * 2;
                    }

                    Thread.sleep(30L);
                    NetProgressBar.this.postInvalidate();

                    if ((NetProgressBar.this.varStatus == 3) && (NetProgressBar.this.progressAngle >= 360))
                    {
                        NetProgressBar.this.drawing = false;
                        if (NetProgressBar.this.finishResultListener != null)
                            NetProgressBar.this.finishResultListener.onFinish(NetProgressBar.this.status == Status.Success, null);
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();

                    if ((NetProgressBar.this.varStatus == 3) && (NetProgressBar.this.progressAngle >= 360))
                    {
                        NetProgressBar.this.drawing = false;
                        if (NetProgressBar.this.finishResultListener != null)
                            NetProgressBar.this.finishResultListener.onFinish(NetProgressBar.this.status == Status.Success, null);
                    }
                }
                finally
                {
                    if ((NetProgressBar.this.varStatus == 3) && (NetProgressBar.this.progressAngle >= 360))
                    {
                        NetProgressBar.this.drawing = false;
                        if (NetProgressBar.this.finishResultListener != null)
                            NetProgressBar.this.finishResultListener.onFinish(NetProgressBar.this.status == Status.Success, null);
                    }
                }
        }
    };

    public NetProgressBar(Context context)
    {
        super(context);
        init();
    }

    public NetProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.backPaint = new Paint();
        this.backPaint.setStyle(Paint.Style.STROKE);
        this.backPaint.setAntiAlias(true);
        this.backPaint.setStrokeWidth(this.strokeWidth);
        this.backPaint.setColor(this.bgColor);

        this.frontPaint = new Paint();
        this.frontPaint.setStyle(Paint.Style.STROKE);
        this.frontPaint.setAntiAlias(true);
        this.frontPaint.setStrokeWidth(this.strokeWidth);
        new Thread(this.runnable).start();
    }

    public void setResult(boolean result)
    {
        this.status = (result ? Status.Success : Status.Failure);
    }

    protected void onDraw(Canvas canvas)
    {
        RectF rectF = new RectF();
        rectF.left = (getPaddingLeft() + this.strokeWidth);
        rectF.right = (getWidth() - getPaddingRight() - this.strokeWidth);
        rectF.top = (getPaddingTop() + this.strokeWidth);
        rectF.bottom = (getHeight() - getPaddingBottom() - this.strokeWidth);
        canvas.drawArc(rectF, 0.0F, 360.0F, false, this.backPaint);

        this.frontPaint.setColor(this.paintColor);
        canvas.rotate(this.angle, rectF.centerX(), rectF.centerY());

        canvas.drawArc(rectF, 0.0F, this.progressAngle, false, this.frontPaint);
    }

    public int getShadeColor(float radio, int mEndColor)
    {
        radio = radio > 1.0F ? 1.0F : radio;
        int redStart = Color.red(this.normalColor);
        int blueStart = Color.blue(this.normalColor);
        int greenStart = Color.green(this.normalColor);
        int redEnd = Color.red(mEndColor);
        int blueEnd = Color.blue(mEndColor);
        int greenEnd = Color.green(mEndColor);

        int red = (int)(redStart + ((redEnd - redStart) * radio + 0.5D));
        int greed = (int)(greenStart + ((greenEnd - greenStart) * radio + 0.5D));
        int blue = (int)(blueStart + ((blueEnd - blueStart) * radio + 0.5D));
        LogUtil.d("getShadeColor : %f  ,red:%d green : %d,blue : %d", new Object[] { Float.valueOf(radio), Integer.valueOf(red), Integer.valueOf(greed), Integer.valueOf(blue) });
        return Color.argb(Color.alpha(this.normalColor), red, greed, blue);
    }

    public void setFinishResultListener(OnFinishResultListener finishResultListener)
    {
        this.finishResultListener = finishResultListener;
    }

    static enum Status
    {
        Normal,
        Success,
        Failure;
    }
}