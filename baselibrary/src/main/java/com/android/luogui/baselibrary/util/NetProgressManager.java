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

package com.android.luogui.baselibrary.util;

import android.support.v4.app.FragmentManager;

import com.android.luogui.baselibrary.ui.NetProgressFragment;


/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class NetProgressManager {

    private static NetProgressFragment fragment;
    private static final String Tag = "net_dialog_fragment";

    public static void start(String hint) {
        start(ActivityManager.getInstance().currentActivity().getSupportFragmentManager(), hint, true);
    }

    public static void start(String hint, boolean canCancel) {
        start(ActivityManager.getInstance().currentActivity().getSupportFragmentManager(), hint, canCancel);
    }

    public static void start(FragmentManager manager, String hint) {
        start(manager, hint, true);
    }

    public static void start(FragmentManager manager, String hint, boolean canCancel) {
        if (fragment == null) {
            fragment = new NetProgressFragment();
        }

        if (fragment.isAdded()) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            fragment.dismissAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }

        fragment.start(manager, "net_dialog_fragment", hint);

        manager.executePendingTransactions();
        fragment.setCanCancel(canCancel);
    }

    public static void dissmiss() {
        if (fragment == null) {
            return;
        }
        fragment.dismissAllowingStateLoss();
    }

    public static void success(String hint) {
        if (fragment == null) {
            return;
        }
        fragment.success(hint);
    }

    public static void failure(String hint) {
        if (fragment == null) {
            return;
        }
        fragment.failure(hint);
    }
}