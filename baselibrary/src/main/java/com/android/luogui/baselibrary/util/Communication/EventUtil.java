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