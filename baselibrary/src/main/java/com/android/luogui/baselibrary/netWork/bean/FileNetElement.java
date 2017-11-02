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