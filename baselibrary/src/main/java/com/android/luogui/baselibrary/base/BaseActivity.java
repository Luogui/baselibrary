package com.android.luogui.baselibrary.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.luogui.baselibrary.util.ActivityManager;
import com.android.luogui.baselibrary.util.Communication.EventReceiver;
import com.android.luogui.baselibrary.util.Communication.EventReceiverInterface;

public class BaseActivity extends AppCompatActivity {

    protected Context context;
    EventReceiver base_Event_Receiver;
    BaseReadyData baseReadyData = new BaseReadyData();

    protected void onReadyFinish() {
        this.baseReadyData.onReadyFinish();
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        ActivityManager.getInstance().addActivity(this);
        if ((this instanceof EventReceiverInterface))
            this.base_Event_Receiver = new EventReceiver(this);
    }

    protected void onResume() {
        super.onResume();
        if ((this instanceof EventReceiverInterface))
            this.base_Event_Receiver.eventRegister(((EventReceiverInterface) this).getTag());
    }

    protected void onPause() {
        super.onPause();
        if ((this instanceof EventReceiverInterface))
            this.base_Event_Receiver.eventunRegister();
    }

    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}