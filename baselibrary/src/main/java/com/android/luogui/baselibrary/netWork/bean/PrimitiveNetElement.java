package com.android.luogui.baselibrary.netWork.bean;

import com.squareup.okhttp.MultipartBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */


public class PrimitiveNetElement extends NetElement
{
    String key;
    String value;

    public PrimitiveNetElement(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public PrimitiveNetElement(String key, int value) {
        this.key = key;
        this.value = String.valueOf(value);
    }

    public void parseJSON(JSONObject object) throws JSONException
    {
        object.put(this.key, this.value == null ? "" : this.value);
    }

    public void parseFormat(MultipartBuilder builder)
    {
        builder.addFormDataPart(this.key, this.value == null ? "" : this.value);
    }

    public String parseValue()
    {
        return this.value + "&";
    }

    public String toString()
    {
        return this.key + " = " + this.value;
    }
}