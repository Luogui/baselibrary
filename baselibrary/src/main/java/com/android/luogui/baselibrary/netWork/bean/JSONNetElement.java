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

package com.android.luogui.baselibrary.netWork.bean;

import com.squareup.okhttp.MultipartBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class JSONNetElement extends NetElement
{
    String key;
    Object value;

    public JSONNetElement(String key, Object value)
    {
        this.key = key;
        this.value = value;
        if ((!(value instanceof JSONObject)) && (!(value instanceof JSONArray)))
            new Throwable("参数只能为JSONObject或JSONArray");
    }

    public void parseJSON(JSONObject object)
            throws JSONException
    {
        object.put(this.key, this.value);
    }

    public void parseFormat(MultipartBuilder builder)
    {
        builder.addFormDataPart(this.key, this.value.toString());
    }

    public String parseValue()
    {
        return null;
    }

    public String toString()
    {
        return this.key + " = " + this.value.toString();
    }
}