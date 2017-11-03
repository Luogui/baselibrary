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
        } catch (JSONException |JsonSyntaxException e) {
            LogUtil.i("Log", e.toString());
        }
        return null;
    }

    public static<T> List<T> parseArrayObject(String content,String key, Class<T> mClass){
        try {
            JSONObject json = new JSONObject(content);
            String value = json.getString(key);
            Type type = new ParamType(mClass);
            return GsonUtil.getGson().fromJson(value,type);
        } catch (JSONException|JsonSyntaxException e) {
            Log.i("Log", e.toString());
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
