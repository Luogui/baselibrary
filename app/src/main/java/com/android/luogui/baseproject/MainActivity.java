package com.android.luogui.baseproject;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.android.luogui.baselibrary.MyApplication;
import com.android.luogui.baselibrary.RuntimePermissions.PermissionsManager;
import com.android.luogui.baselibrary.RuntimePermissions.PermissionsResultAction;
import com.android.luogui.baselibrary.netWork.retrofit.ApiClint;
import com.android.luogui.baselibrary.util.DialogUtil;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baseproject.bean.BuyInHouseBean;
import com.android.luogui.baseproject.down.DownActivity;
import com.android.luogui.baseproject.xFragment.XFragmentActivity;
import com.arialyy.aria.core.Aria;
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
    private ProgressView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        BuyInHouseBean buyInHouseBean = new BuyInHouseBean();
        buyInHouseBean.setSerialNumber("1");
        buyInHouseBean.setItemNumber("2");
        buyInHouseBean.setQty(3);
        buyInHouseBean.setInvCode("selectHouseBean.getInvCode()");
        buyInHouseBean.setVenderCode("selectSupplierBean.getVenderCode()");


        ApiClint.createJsonApiString(ApiService.class)
                .buyInHouse(ApiService.header, buyInHouseBean)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        int code = jsonObject.getIntValue("code");
                        if (code != 200) {
                            LogUtil.toast(jsonObject.getString("message"));
                            return;
                        }else {
                            LogUtil.toast(jsonObject.getString("message"));
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


        setContentView(R.layout.activity_main);

        qqLogin = new QQLogin(MainActivity.this);
        loginListener = qqLogin.getLoginListener();
        lv = findViewById(R.id.lv);

        lv.setProgress(50);


        listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getList()));
        listView.setOnItemClickListener((adapterView, view, i, l) -> toStart(i));

        PermissionsManager.getInstance().
                requestPermissionsIfNecessaryForResult(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_EXTERNAL_STORAGE},
                        new PermissionsResultAction() {
                            @Override
                            public void onGranted() {
                                LogUtil.i("123456");
//                                finish();
                            }

                            @Override
                            public void onDenied(String permission) {
                                LogUtil.i("789456");
//                                startActivity(new Intent(context, MainActivity.class));
//                                finish();
                            }
                        });
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
                    public void result(DialogInterface dialog, String s) {

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
            case 6:
                statDown();
                break;
            case 7:
                startActivity(new Intent(MainActivity.this, DownActivity.class));
                break;
//                http://www.nnajlaw.com:5005/user/header
        }
    }


    private void statDown(){
        String path = Environment.getExternalStorageDirectory() + "/" + MyApplication.getInstance().getString(R.string.app_name) + "/";
        String[] downArr = {
                "http://p8srlm46t.bkt.clouddn.com/05-%E8%A3%85%E9%A5%B0%E5%99%A8.flv",
                "http://p8srlm46t.bkt.clouddn.com/01-%E8%BF%AD%E4%BB%A3%E5%99%A8.flv",
                "http://p8srlm46t.bkt.clouddn.com/04-%E9%97%AD%E5%8C%85-%E5%BA%94%E7%94%A8.flv",
                "http://p8srlm46t.bkt.clouddn.com/02-%E9%97%AD%E5%8C%85.flv"
        };
        for (int i = 0; i < downArr.length; i++) {

            path =   path + "A/";
            File file = new File(path);
            if (!file.exists()) file.mkdirs();
            path = path + i +  ".flv";

            Aria.download(this)
                    .load(downArr[i])     //读取下载地址
                    .setFilePath(path) //设置文件保存的完整路径
                    .start();
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
        arrayList.add("fileDown");
        arrayList.add("fileDownList");
        return arrayList;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }


}
