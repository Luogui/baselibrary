package com.android.luogui.baselibrary.netWork.bean;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class FileByteNetElement extends FileNetElement
{
    String key;
    byte[] value;
    String fileName;

    public FileByteNetElement(String key, byte[] value)
    {
        this.key = key;
        this.value = value;
    }

    public FileByteNetElement(String key, byte[] value, String fileName) {
        this.key = key;
        this.value = value;
        this.fileName = fileName;
    }

    public void parseFormat(MultipartBuilder builder)
    {
        if (this.fileName == null)
            builder
                    .addFormDataPart(this.key, "name.png",
                            RequestBody.create(MediaType.parse("image/png"),
                                    this.value));
        else
            builder.addFormDataPart(this.key, this.fileName,
                    RequestBody.create(MediaType.parse(guessMimeType(this.fileName)),
                            this.value));
    }

    public String toString()
    {
        return this.fileName + " : " + this.value.length;
    }
}