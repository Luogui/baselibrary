/*
 * Copyright (c) 2017.
 * Create by LuoGui.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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