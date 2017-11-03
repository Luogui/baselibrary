package com.android.luogui.baseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<String> string = ApiClint.getApi().getString("");
    }
}
