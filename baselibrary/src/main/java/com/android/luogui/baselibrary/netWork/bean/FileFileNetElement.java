package com.android.luogui.baselibrary.netWork.bean;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import java.io.File;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class FileFileNetElement extends FileNetElement
{
    String key;
    File file;

    public FileFileNetElement(String key, File file)
    {
        this.key = key;
        this.file = file;
    }

    public void parseFormat(MultipartBuilder builder)
    {
        builder.addFormDataPart(this.key, this.file
                        .getName(),
                RequestBody.create(MediaType.parse(guessMimeType(this.file
                        .getName())), this.file));
    }

    public String toString()
    {
        return this.key + " = [file]" + this.file.getName();
    }
}