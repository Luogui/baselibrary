package com.android.luogui.baseproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.luogui.baselibrary.base.BaseListActivity;
import com.android.luogui.baselibrary.mInterface.OnItemClickListener;
import com.android.luogui.baselibrary.netWork.retrofit.ResultCallBack;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baseproject.XFragment.XFragmentActivity;
import com.android.luogui.baseproject.adapter.XAdapter;
import com.android.luogui.baseproject.bean.NewsBean;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private IUiListener loginListener;
    private QQLogin qqLogin;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qqLogin = new QQLogin(MainActivity.this);
        loginListener = qqLogin.getLoginListener();

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getList()));
        listView.setOnItemClickListener((adapterView, view, i, l) -> toStart(i));

    }

    private void toStart(int i){
        switch (i){
            case 0:
                //XFragment
                startActivity(new Intent(this, XFragmentActivity.class));
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
    }
    public List<String> getList() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("XFragment");
        return arrayList;
    }




}
