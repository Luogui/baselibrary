package com.android.luogui.baseproject.XFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.luogui.baselibrary.base.SlideActivity;
import com.android.luogui.baseproject.R;

public class XFragmentActivity extends SlideActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfragment, false);

        MyFragment myFragment = new MyFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, myFragment).commit();
    }

}
