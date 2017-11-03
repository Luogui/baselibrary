package com.android.luogui.baselibrary.LayerAdapter;

import android.view.View;

/**
 * describe
 * Created by LuoGui on 2017/4/10.
 */

public interface OnGroupClickListener {
    /**
     * @param view  点击的view
     * @param group
     * @param isTop 是顶部还是底部
     */
    void onGroupClick(View view, int group, boolean isTop);
}
