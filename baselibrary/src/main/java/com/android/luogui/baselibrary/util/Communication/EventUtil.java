package com.android.luogui.baselibrary.util.Communication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class EventUtil {

    public static void sendEvent(Context context, String Tag, Bundle bundle) {
        Intent intent = new Intent("BaseProjectLib." + Tag);
        intent.setPackage(context.getPackageName());
        if (bundle != null) {
            intent.putExtra("bundle_param", bundle);
        }
        context.sendBroadcast(intent);
    }

}