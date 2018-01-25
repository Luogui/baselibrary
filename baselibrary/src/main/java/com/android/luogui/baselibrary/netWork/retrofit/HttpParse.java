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

package com.android.luogui.baselibrary.netWork.retrofit;

import android.util.Log;

import com.android.luogui.baselibrary.util.LogUtil;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型解析
 * Created by LuoGui on 2017/3/13.
 */

public class HttpParse {

    public static<T> T parseObject(String content,Class<T> mClass){
        return GsonUtil.getGson().fromJson(content,mClass);
    }

    public static<T> T parseObject(String content, String key, Class<T> mClass){
        try {
            JSONObject json = new JSONObject(content);
            String value = json.getString(key);
            return GsonUtil.getGson().fromJson(value,mClass);
        } catch (JSONException |JsonSyntaxException |NullPointerException e) {
            LogUtil.e(e.toString());
        }
        return null;
    }

    public static<T> List<T> parseArrayObject(String content,String key, Class<T> mClass){
        try {
            JSONObject json = new JSONObject(content);
            String value = json.getString(key);
            Type type = new ParamType(mClass);
            return GsonUtil.getGson().fromJson(value,type);
        } catch (JSONException|JsonSyntaxException|NullPointerException e) {
            LogUtil.e(e.toString());
        }
       return new ArrayList<>();
    }

    public static<T> List<T> parseArrayObject(String content, Class<T> mClass){
        Type type = new ParamType(mClass);
        return GsonUtil.getGson().fromJson(content,type);
    }

    private Type getType(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        return actualTypeArguments[0];

    }
}
