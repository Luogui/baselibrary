package com.android.luogui.baselibrary.util.Communication;

import android.os.Bundle;

/**
 * 描述：
 * Created by LuoGui on 2017/8/25.
 */

public abstract interface EventReceiverInterface {

        public abstract void onDispose(String paramString, Bundle paramBundle);

        public abstract String[] getTag();

}
