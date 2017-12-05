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
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;


/**
 * //   文字   文字   >
 * Created by LG on 2017/2/28.
 */

public class TextViewLay extends LinearLayout {

    Context context;
    TextView tvLeft;
    TextView tvRight;
    ImageView ivArrow;
    ImageView ivLeft;

    public TextViewLay(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TextViewLay(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewLay);

        String leftText = a.getString(R.styleable.TextViewLay_leftText);
        String rightText = a.getString(R.styleable.TextViewLay_rightText);
        tvLeft.setText(leftText);
        tvRight.setText(rightText);

        int leftColor = a.getColor(R.styleable.TextViewLay_leftTextColor, Color.BLACK);
        int rightColor = a.getColor(R.styleable.TextViewLay_rightTextColor, Color.GRAY);
        tvLeft.setTextColor(leftColor);
        tvRight.setTextColor(rightColor);

        int arrow = a.getResourceId(R.styleable.TextViewLay_arrow,-1);
        if (arrow!=-1) {
            ivArrow.setImageResource(arrow);
            ivArrow.setVisibility(VISIBLE);
        }

        int leftIcon = a.getResourceId(R.styleable.TextViewLay_leftIcon,-1);
        if (leftIcon!=-1) {
            ivLeft.setImageResource(leftIcon);
            ivLeft.setVisibility(VISIBLE);
        }


        float leftTextSize = a.getDimensionPixelSize(R.styleable.TextViewLay_leftTextSize,48);
        float rightTextSize = a.getDimension(R.styleable.TextViewLay_rightTextSize,48);
        tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,leftTextSize);
        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);

        a.recycle();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_text_lay, this, true);
        tvLeft = (TextView) view.findViewById(R.id.tv_left_text);
        tvRight = (TextView) view.findViewById(R.id.tv_right_text);
        ivArrow = (ImageView) view.findViewById(R.id.iv_arrow);
        ivLeft = (ImageView) view.findViewById(R.id.iv_left);
    }

    public void setRightText(String text){
        tvRight.setText(text);
    }

    public void setRightGravity(int gravity){
        tvRight.setGravity(Gravity.CENTER_VERTICAL | gravity);
    }


}
