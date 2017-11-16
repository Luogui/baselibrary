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

package com.android.luogui.baselibrary.util.Communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class EventReceiver {

    Context context;
    EventDispose receiver;
    EventReceiverInterface receiverInterface;

    public EventReceiver(Context context) {
        this.context = context;
        if ((context instanceof EventReceiverInterface)) {
            this.receiverInterface = ((EventReceiverInterface) context);
            this.receiver = new EventDispose();
        }
    }

    public void eventRegister(String[] Tag) {
        if (this.receiverInterface != null) {
            IntentFilter filter = new IntentFilter();

            for (String s : Tag) {
                filter.addAction("BaseProjectLib." + s);
            }
            this.context.registerReceiver(this.receiver, filter);
        }
    }

    public void eventunRegister() {
        if (this.receiverInterface != null)
            this.context.unregisterReceiver(this.receiver);
    }

    private class EventDispose extends BroadcastReceiver {
        private EventDispose() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getPackage().equals(EventReceiver.this.context.getPackageName())) {
                String action = intent.getAction();
                String tag = intent.getAction().substring("BaseProjectLib.".length());
                EventReceiver.this.receiverInterface.onDispose(tag, intent.getBundleExtra("bundle_param"));
            }
        }
    }
}