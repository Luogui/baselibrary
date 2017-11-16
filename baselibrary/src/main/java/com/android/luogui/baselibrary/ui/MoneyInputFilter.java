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
