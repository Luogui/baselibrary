package com.android.luogui.baseproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.luogui.baselibrary.base.BaseListActivity;
import com.android.luogui.baselibrary.mInterface.OnItemClickListener;
import com.android.luogui.baselibrary.netWork.retrofit.ResultCallBack;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baseproject.adapter.XAdapter;
import com.android.luogui.baseproject.bean.NewsBean;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private IUiListener loginListener;
    private QQLogin qqLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qqLogin = new QQLogin(MainActivity.this);
        loginListener = qqLogin.getLoginListener();

        findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqLogin.login();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
    }



}
