package com.android.luogui.baseproject.XFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.luogui.baseproject.R;

public class XFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfragment);

        MyFragment myFragment = new MyFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, myFragment).commit();
    }
}
