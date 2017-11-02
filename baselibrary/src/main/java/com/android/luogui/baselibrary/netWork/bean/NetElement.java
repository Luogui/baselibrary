package com.android.luogui.baselibrary.netWork.bean;

import com.squareup.okhttp.MultipartBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract class NetElement
{
    public abstract void parseJSON(JSONObject paramJSONObject)
            throws JSONException;

    public abstract void parseFormat(MultipartBuilder paramMultipartBuilder);

    public abstract String parseValue();
}