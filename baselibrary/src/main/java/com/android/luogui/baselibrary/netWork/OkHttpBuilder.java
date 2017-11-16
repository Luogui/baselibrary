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

package com.android.luogui.baselibrary.netWork;

import android.util.Base64;

import com.android.luogui.baselibrary.Setting;
import com.android.luogui.baselibrary.decryption.AES;
import com.android.luogui.baselibrary.decryption.RSA;
import com.android.luogui.baselibrary.netWork.bean.FileByteNetElement;
import com.android.luogui.baselibrary.netWork.bean.FileFileNetElement;
import com.android.luogui.baselibrary.netWork.bean.JSONNetElement;
import com.android.luogui.baselibrary.netWork.bean.NetElement;
import com.android.luogui.baselibrary.netWork.bean.PrimitiveNetElement;
import com.android.luogui.baselibrary.util.LogUtil;
import com.squareup.okhttp.Request;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class OkHttpBuilder {

    String url;
    Map<String, List<String>> heads = new HashMap();
    List<NetElement> elementList = new ArrayList();

    private Object bindParam = null;
    RequestLife requestLife;
    Object liftParam;
    boolean hasEncrypt = false;
    String secrect;

    public OkHttpBuilder addParam(String key, File[] value) {
        for (File f : value) {
            this.elementList.add(new FileFileNetElement(key, f));
        }
        return this;
    }

    public OkHttpBuilder addFileParam(String key, String[] value) {
        for (String f : value) {
            this.elementList.add(new FileFileNetElement(key, new File(f)));
        }
        return this;
    }

    public OkHttpBuilder addParamArray(String key, String[] value) {
        JSONArray array = new JSONArray();

        for (String f : value) {
            array.put(f);
        }
        addParamObject(key, new Object[]{array});
        return this;
    }

    public OkHttpBuilder addParam(String key, byte[][] value) {
        for (byte[] f : value) {
            this.elementList.add(new FileByteNetElement(key, f));
        }
        return this;
    }

    public OkHttpBuilder addParam(String key, byte[] value, String fileName) {
        this.elementList.add(new FileByteNetElement(key, value, fileName));
        return this;
    }

    public OkHttpBuilder addParam(String key, String[] value) {
        for (String f : value) {
            this.elementList.add(new PrimitiveNetElement(key, f));
        }
        return this;
    }

    public OkHttpBuilder addHead(String key, String[] value) {
        if (this.heads.get(key) == null) {
            this.heads.put(key, new ArrayList());
        }
        ((List) this.heads.get(key)).addAll(Arrays.asList(value));
        return this;
    }

    public OkHttpBuilder addParam(String key, int[] value) {
        for (int f : value) {
            this.elementList.add(new PrimitiveNetElement(key, f));
        }
        return this;
    }

    public OkHttpBuilder addParamObject(String key, Object[] value) {
        for (Object f : value) {
            this.elementList.add(new JSONNetElement(key, f));
        }
        return this;
    }

    public OkHttpBuilder addElement(NetElement element) {
        this.elementList.add(element);
        return this;
    }

    public OkHttpBuilder url(String url) {
        this.url = url;
        return this;
    }

    public OkHttpBuilder bindWithObject(Object object) {
        this.bindParam = object;
        return this;
    }

    public Object getBindParam() {
        return this.bindParam;
    }

    public void executeJsonPost(OkHttpManger.ResultCallback callback) {
        if (this.requestLife != null) {
            this.liftParam = this.requestLife.OnStart();
        }
        OkHttpManger.getInstance().executeJsonPost(this.url, this, new CallBack(callback));
    }

    public void executeFormPost(OkHttpManger.ResultCallback callback) {
        if (this.requestLife != null) {
            this.liftParam = this.requestLife.OnStart();
        }
        OkHttpManger.getInstance().enqueueFormPost(this.url, this, new CallBack(callback));
    }

    public void executeGetParams(OkHttpManger.ResultCallback callback) {
        if (this.requestLife != null) {
            this.liftParam = this.requestLife.OnStart();
        }
        OkHttpManger.getInstance().executeGet(this.url, this, new CallBack(callback));
    }

    public void setRequestLife(RequestLife requestLife) {
        this.requestLife = requestLife;
    }

    public boolean isHasEncrypt() {
        return this.hasEncrypt;
    }

    public void setHasEncrypt(boolean hasEncrypt) {
        this.hasEncrypt = hasEncrypt;
    }

    public String encrppt(String result) {
        if (!this.hasEncrypt) {
            return result;
        }

        try {
            Map map = null;
            map = AES.encrypt(result);

            this.secrect = ((String) map.get("key"));

            String key = Base64.encodeToString(RSA.encryptByPublicKey(((String) map.get("key")).getBytes(),
                    Setting.RSAPublicKey.getBytes()), Base64.DEFAULT);
            addHead("key", new String[]{URLEncoder.encode(key, "UTF-8")});

            return (String) map.get("data");
        } catch (Exception e) {
            LogUtil.t("数据加密失败", e);
        }
        return "";
    }

    public String decrypt(String result) {
        if (!this.hasEncrypt) {
            return result;
        }

        return AES.decrypt(this.secrect, result);
    }

    public static abstract interface RequestLife {
        public abstract Object OnStart();

        public abstract void OnFinish(Object paramObject, OkHttpBuilder paramOkHttpBuilder, boolean paramBoolean, String paramString);
    }

    private class CallBack extends OkHttpManger.ResultCallback {
        OkHttpManger.ResultCallback callback;

        public CallBack(OkHttpManger.ResultCallback callback) {
            this.callback = callback;
        }

        public void onFailure(Request request, Exception e) {
            if (this.callback != null) {
                if (OkHttpBuilder.this.requestLife != null) {
                    OkHttpBuilder.this.requestLife.OnFinish(OkHttpBuilder.this.liftParam, OkHttpBuilder.this, false, e.getMessage());
                }

                this.callback.onFailure(request, e);
            }
        }

        public void onSuccess(String content) {
            if (this.callback != null) {
                String result = OkHttpBuilder.this.decrypt(content);
                if (OkHttpBuilder.this.requestLife != null) {
                    OkHttpBuilder.this.requestLife.OnFinish(OkHttpBuilder.this.liftParam, OkHttpBuilder.this, true, result);
                }
                this.callback.onSuccess(result);
            }
        }
    }
}
