package com.android.luogui.baselibrary.ui;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import com.android.luogui.baselibrary.util.LogUtil;


/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class MoneyInputFilter
        implements InputFilter
{
    private int point_length = 2;

    public MoneyInputFilter(int point_length) {
        this.point_length = point_length;
    }

    public MoneyInputFilter()
    {
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        LogUtil.d("source : %1$s  ,dest : %2$s  ,end : %3$d  , dend : %4$d,start %5$d,dstart %6$d", new Object[] { source
                .toString(), dest.toString(), Integer.valueOf(end), Integer.valueOf(dend), Integer.valueOf(start), Integer.valueOf(dstart) });
        String destString = dest.toString();
        String sourceString = source.toString();
        if (TextUtils.isEmpty(sourceString)) {
            return null;
        }
        int point = destString.indexOf(".");
        if ((point != -1) && (dstart > point) &&
                (end - start - (dend - dstart) + destString.length() - point > this.point_length + 1)) {
            return "";
        }

        return null;
    }
}
