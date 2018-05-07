package com.android.luogui.baseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.luogui.baselibrary.netWork.retrofit.ApiClint;
import com.android.luogui.baselibrary.util.DialogUtil;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baseproject.xFragment.XFragmentActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getList()));
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
                DialogUtil.showTip(this, "提示", "是否兑换", new DialogUtil.DialogResult() {
                    @Override
                    public void result(String s) {

                    }
                });
                break;
            case 3:
                startActivity(new Intent(this, CatchRecordActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, EmptyActivity.class));
                break;
            case 5:
                File file = new File("/storage/emulated/0/Pictures/Screenshots/S80419-195752.jpg");
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                ApiClint.createApi(ApiService.class).upHeader("1241095", part)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                LogUtil.i(response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                break;
//                http://www.nnajlaw.com:5005/user/header
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
        arrayList.add("222");
        arrayList.add("test");
        arrayList.add("test2");
        arrayList.add("AddHeader");
        return arrayList;
    }


}
