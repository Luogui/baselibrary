package com.android.luogui.baselibrary.LayerAdapter;

/**
 * describe
 * Created by LuoGui on 2017/4/10.
 */

public class LayerIndexPath {
    int group;
    int child;
    boolean isBottom;

    public LayerIndexPath() {
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public boolean isBottom() {
        return isBottom;
    }

    public void setBottom(boolean bottom) {
        isBottom = bottom;
    }
}
