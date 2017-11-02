package com.android.luogui.baselibrary.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.luogui.baselibrary.R;

/**
 * 描述： 数量增减
 * Created by LuoGui on 2017/8/28.
 */

public class CountView extends LinearLayout
{
    Context context;
    ImageView reduce_iv;
    ImageView add_iv;
    EditText number_et;
    int max = 2147483647;
    int min = 1;
    NumChange numChange;

    public CountView(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(this.context).inflate(R.layout.base_count_view, this, true);
        this.reduce_iv = ((ImageView)findViewById(R.id.reduce));
        this.add_iv = ((ImageView)findViewById(R.id.add));
        this.number_et = ((EditText)findViewById(R.id.number));
        setNumber(this.min);

        this.number_et.setOnFocusChangeListener(new OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus) {
                int num = CountView.this.getNumber();
                if (num > CountView.this.max)
                    CountView.this.setNumber(CountView.this.max);
                else if (num < CountView.this.min)
                    CountView.this.setNumber(CountView.this.min);
            }
        });
        this.reduce_iv.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view) {
                int num = CountView.this.getNumber();
                num--; CountView.this.setNumber(num <= CountView.this.min ? CountView.this.min : num);
            }
        });
        this.add_iv.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view) {
                int num = CountView.this.getNumber();
                num++; CountView.this.setNumber(num >= CountView.this.max ? CountView.this.max : num);
            }
        });
    }

    public void setNumber(int number)
    {
        this.number_et.setText(String.valueOf(number));
        if (this.numChange != null)
            this.numChange.onNumChange(number);
    }

    public void setMaxNumber(int max)
    {
        if (max < this.min) {
            max = this.min;
        }
        this.max = max;
        if (getNumber() > max)
            setNumber(max);
    }

    public void setMinNumber(int min)
    {
        if (min > this.max) {
            min = this.max;
        }
        this.min = min;
        if (getNumber() < min)
            setNumber(min);
    }

    public int getNumber()
    {
        int number = this.min;
        String num = this.number_et.getText().toString();
        if (!num.equals("")) {
            number = Integer.parseInt(num);
        }
        return number;
    }

    public ImageView getReduce_iv() {
        return this.reduce_iv;
    }

    public ImageView getAdd_iv() {
        return this.add_iv;
    }

    public EditText getNumber_et() {
        return this.number_et;
    }

    public void setNumChange(NumChange numChange)
    {
        this.numChange = numChange;
    }

    public static abstract interface NumChange
    {
        public abstract void onNumChange(int paramInt);
    }
}