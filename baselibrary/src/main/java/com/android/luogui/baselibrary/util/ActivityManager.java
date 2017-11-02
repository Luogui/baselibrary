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