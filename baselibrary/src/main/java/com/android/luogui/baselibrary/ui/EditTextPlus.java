package com.android.luogui.baselibrary.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.android.luogui.baselibrary.R;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class EditTextPlus extends android.support.v7.widget.AppCompatEditText
{
    private Context context;
    private Drawable drawable;
    private Boolean canClear = Boolean.valueOf(false);

    public EditTextPlus(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public EditTextPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        this.canClear = Boolean.valueOf(array.getBoolean(R.styleable.EditTextPlus_canClear, false));
        init();
    }

    private void init()
    {
        if (this.canClear.booleanValue()) {
            this.drawable = getResources().getDrawable(R.drawable.ic_cancel);
            this.drawable.setBounds(0, 0, (int)getTextSize(), (int)getTextSize());
        }

        addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            public void afterTextChanged(Editable editable)
            {
                if (EditTextPlus.this.canClear.booleanValue())
                    if (editable.toString().trim().length() == 0)
                        EditTextPlus.this.setCompoundDrawables(null, null, null, null);
                    else
                        EditTextPlus.this.setCompoundDrawables(null, null, EditTextPlus.this.drawable, null);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if ((getText().toString().trim().length() != 0) && (event.getAction() == 1) &&
                (this.canClear) &&
                (event.getX() > getWidth() - this.drawable.getMinimumWidth() - getPaddingRight())) {
            setText("");
            event.setAction(3);
        }

        return super.onTouchEvent(event);
    }
}
