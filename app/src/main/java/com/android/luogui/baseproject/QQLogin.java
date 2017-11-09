package com.android.luogui.baseproject;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by LG on 2016/9/20.
 */
public class QQLogin {

    private MainActivity context;
    private Tencent mTencent;
    private String openId;
    // 申请的id
    private String mAppId = "1106170314";
    private IUiListener loginListener;
    private final String SCOPE = "all"; //获取信息的范围参数 拿取所以

    public QQLogin(Activity context){
        this.context = (MainActivity)context;
        // Tencent类是SDK的主要实现类，通过此访问腾讯开放的OpenAPI。
        Tencent.createInstance(mAppId, context.getApplicationContext());
        // 实例化
        mTencent = Tencent.createInstance(mAppId, context);
        initData();
    }
    public IUiListener getLoginListener(){
        return loginListener;
    }



    //授权登录监听
    public void initData() {
        //初始化qq主操作对象
        mTencent = Tencent.createInstance(mAppId, context);
        //要所有权限，不然会再次申请增量权限，这里不要设置成get_user_info,add_t
        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();
            }

            /**
             * 返回json数据样例
             *
             * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
             * "pf":"desktop_m_qq-10000144-android-2002-",
             * "query_authority_cost":448,
             * "authority_cost":-136792089,
             * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
             * "expires_in":7776000,
             * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
             * "msg":"",
             * "access_token":"A2455F491478233529D0106D2CE6EB45",
             * "login_cost":499}
             */
            @Override
            public void onComplete(Object value) {
                if (value == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) value;
                    int ret = jo.getInt("ret");
                    Log.i("Log111", jo.toString());
                    System.out.println("json=" + String.valueOf(jo));
                    if (ret == 0) {
                        Toast.makeText(context, "登录成功",
                                Toast.LENGTH_LONG).show();

                        openId = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");


//                        context.qqLogin(openId, accessToken,2);
//                        mTencent.setOpenId(openId);
//                        mTencent.setAccessToken(accessToken, expires);
//                        getUserInfo();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancel() {
                Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();
            }
        };
    }
    public void login() {
        //如果session无效，就开始登录
//        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login(context, SCOPE, loginListener);
//        }
    }
    private void getUserInfo() {
        new UserInfo(context, mTencent.getQQToken()).getUserInfo(getInfoListener);
    }
    /**
     * 获取用户信息
     */
    private IUiListener getInfoListener = new IUiListener() {
        @Override
        public void onComplete(Object response) {
            try {
                JSONObject jsonObject = (JSONObject) response;
                Log.i("Log", jsonObject.toString());
                //名字
                String name = jsonObject.getString("nickname");
                //头像
                String headImg = jsonObject.getString("figureurl_qq_2");
                //性别
                String sex = jsonObject.getString("gender");
                //处理自己需要的信息
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    };

}
