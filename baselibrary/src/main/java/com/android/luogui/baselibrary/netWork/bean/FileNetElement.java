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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.FileNameMap;
import java.net.URLConnection;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public abstract class FileNetElement extends NetElement
{
    public void parseJSON(JSONObject object)
            throws JSONException
    {
    }

    public String parseValue()
    {
        return null;
    }

    protected String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}