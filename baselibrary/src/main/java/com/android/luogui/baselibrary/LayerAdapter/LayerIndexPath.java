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
