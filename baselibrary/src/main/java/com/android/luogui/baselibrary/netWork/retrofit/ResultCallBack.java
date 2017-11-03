package com.android.luogui.baselibrary.netWork.retrofit;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.luogui.baselibrary.MyApplication;
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
                    T t = GsonUtil.getGson().fromJson(jsonObject.optString("content"),  type);
                    if (t!=null){
                        onSuccess(t);
                    }
                }else if (code==301){
                    //token验证失败
                    onFailed(code, jsonObject.optString("msg"));
//                    Activity activity = ActivityManager.getInstance().currentActivity();
//                    activity.startActivity(new Intent(activity, RecycleActivity.class));
                    Toast.makeText(MyApplication.getInstance(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
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
