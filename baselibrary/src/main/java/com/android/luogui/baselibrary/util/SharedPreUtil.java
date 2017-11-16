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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class SharedPreUtil {

    Context context;
    private SharedPreferences sharedPreferences;
    private static SharedPreUtil instance;
    private static String NAME = "setting";

    public static SharedPreUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreUtil.class) {
                if (instance == null) {
                    instance = new SharedPreUtil(context);
                }
            }
        }
        return instance;
    }

    private SharedPreUtil(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharedPreUtil saveParam(String key, Object value) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        if ((value instanceof String))
            editor.putString(key, (String) value);
        else if ((value instanceof Integer))
            editor.putInt(key, ((Integer) value).intValue());
        else if ((value instanceof Long))
            editor.putLong(key, ((Long) value).longValue());
        else if ((value instanceof Boolean))
            editor.putBoolean(key, ((Boolean) value).booleanValue());
        else if ((value instanceof Float))
            editor.putFloat(key, ((Float) value).floatValue());
        else {
            new Throwable("错误的类型!");
        }
        editor.commit();
        return instance;
    }

    public <T> T getParam(String key, Class<T> clzss) {
        if (clzss.equals(String.class))
            return (T) this.sharedPreferences.getString(key, "");
        if (clzss.equals(Integer.class))
            return (T) Integer.valueOf(this.sharedPreferences.getInt(key, 0));
        if (clzss.equals(Long.class))
            return (T) Long.valueOf(this.sharedPreferences.getLong(key, 0L));
        if (clzss.equals(Boolean.class))
            return (T) Boolean.valueOf(this.sharedPreferences.getBoolean(key, false));
        if (clzss.equals(Float.class)) {
            return (T) Float.valueOf(this.sharedPreferences.getFloat(key, 0.0F));
        }
        return null;
    }

    public SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public void clear() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public int getInt(String key) {
        return this.sharedPreferences.getInt(key, 0);
    }

    public long getLong(String key) {
        return this.sharedPreferences.getLong(key, 0L);
    }

    public boolean getBoolean(String key) {
        return this.sharedPreferences.getBoolean(key, false);
    }

    public String getString(String key) {
        return this.sharedPreferences.getString(key, "");
    }

    public float getFloat(String key) {
        return this.sharedPreferences.getFloat(key, 0.0F);
    }
}
