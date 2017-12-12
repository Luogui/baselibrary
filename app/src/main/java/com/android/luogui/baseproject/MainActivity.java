package com.android.luogui.baseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.luogui.baselibrary.util.DialogUtil;
import com.android.luogui.baseproject.XFragment.XFragmentActivity;
import com.android.luogui.baseproject.slide.SlideActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

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

        //http://photos.shangweiled.com/figure_2015a_0444_01.jpg,http://photos.shangweiled.com/figure_2015a_0444_02.jpg,http://photos.shangweiled.com/figure_2015a_0444_03.jpg,http://photos.shangweiled.com/figure_2015a_0444_04.jpg,http://photos.shangweiled.com/figure_2015a_0444_05.jpg,http://photos.shangweiled.com/figure_2015a_0444_06.jpg

    }

    private void toStart(int i){
        switch (i){
            case 0:
                //BaseListFragment
                startActivity(new Intent(this, XFragmentActivity.class));
                break;
            case 1:
                //BaseListFragment
                startActivity(new Intent(this, SwipeActivity.class));
                break;
            case 2:
                //SlideActivity
//                startActivity(new Intent(this, SlideActivity.class));
                DialogUtil.showTip(this, "提示", "是否兑换", new DialogUtil.DialogResult() {
                    @Override
                    public void result(String s) {

                    }
                });
            case 3:
                //SlideActivity
                startActivity(new Intent(this, CatchRecordActivity.class));
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
        arrayList.add("BaseListFragment");
        arrayList.add("SwipeActivity");
        arrayList.add("SlideActivity");
        arrayList.add("test");
        return arrayList;
    }


}
