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