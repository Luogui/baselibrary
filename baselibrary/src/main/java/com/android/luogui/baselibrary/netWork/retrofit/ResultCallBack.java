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

package com.android.luogui.baselibrary.netWork.retrofit;

import android.util.Base64;
import android.util.Log;

import com.android.luogui.baselibrary.constant.Constant;
import com.android.luogui.baselibrary.decryption.AES;
import com.android.luogui.baselibrary.decryption.RSA;
import com.android.luogui.baselibrary.util.StringUtil;
import org.json.JSONObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * describe
 * Created by  LuoGui on 2017/9/19.
 */

public abstract class ResultCallBack<T> implements Callback<String> {

    private String key;


    protected ResultCallBack(String key) {
        this.key = key;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (null!=response.body()){
            try {
                String result = response.body();
                if (Constant.AES_ENCRYPT) {
                    result = AES.decryptString(response.body());
                }else if (Constant.RSA_ENCRYPT) {
                    result = new String(RSA.decryptByPrivateKeyForSpilt(Base64.decode(response.body(),
                            Base64.DEFAULT), RSA.getPrivateKey(Base64
                            .decode(Constant.RSA_PRIVATE_KEY, Base64.DEFAULT)).getEncoded()));
                }
                if (StringUtil.isEmpty(result)){
                    return;
                }
                JSONObject jsonObject = new JSONObject(result);
                int code = jsonObject.optInt("code");
                if (code==200){
                    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
                    Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
                    Type type = actualTypeArguments[0];
                    T t = GsonUtil.getGson().fromJson(jsonObject.optString(key),  type);
                    if (t!=null){
                        onSuccess(t);
                    }
                }else if (code==301){
                    //token验证失败
                }
                else {
                    onFailed(code, jsonObject.optString("msg"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("error", e.toString());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailed(int code, String s);


}
