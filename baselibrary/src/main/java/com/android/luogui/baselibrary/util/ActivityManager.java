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

import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 * 描述： activity管理类
 * Created by LuoGui on 2017/8/25.
 */

public class ActivityManager {

    private static Stack<AppCompatActivity> sActivityStack = new Stack();
    private static ActivityManager instance;

    public static synchronized ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    public void addActivity(AppCompatActivity activity) {
        sActivityStack.add(activity);
    }

    public void removeActivity(AppCompatActivity activity) {
        if (activity != null)
            sActivityStack.remove(activity);
    }

    public void exit() {
        while (!sActivityStack.empty()) {
            AppCompatActivity activity = currentActivity();
            activity.finish();
            sActivityStack.remove(activity);
        }
        System.exit(0);
    }

    public void exit(Class<AppCompatActivity> cls) {
        while (!sActivityStack.empty()) {
            AppCompatActivity activity = currentActivity();
            if (activity.getClass().equals(cls)) {
                break;
            }
            activity.finish();
            sActivityStack.remove(activity);
        }
    }

    public void exit(int page) {
        for (int i = 0; (i < page) && (!sActivityStack.empty()); i++) {
            AppCompatActivity activity = currentActivity();
            activity.finish();
            sActivityStack.remove(activity);
        }
    }

    public AppCompatActivity currentActivity() {
        AppCompatActivity activity = null;
        if (!sActivityStack.empty())
            activity = (AppCompatActivity) sActivityStack.lastElement();
        return activity;
    }

}