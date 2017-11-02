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