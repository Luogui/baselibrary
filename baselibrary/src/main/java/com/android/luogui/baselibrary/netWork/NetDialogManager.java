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

package com.android.luogui.baselibrary.netWork;

import android.content.Context;

import com.android.luogui.baselibrary.ui.NetProgressDialog;
import com.android.luogui.baselibrary.util.LogUtil;


/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class NetDialogManager {

    private static NetDialogManager instance;
    private NetDialogInterface dialog;

    public static NetDialogManager getInstance() {
        if (instance == null) {
            synchronized (NetDialogManager.class) {
                if (instance == null) {
                    instance = new NetDialogManager();
                }
            }
        }
        return instance;
    }

    private void init(Context context) {
        this.dialog = new NetProgressDialog(context);
    }

    public void setDialog(NetProgressDialog dialog) {
        this.dialog = dialog;
    }

    public void dialogShow() {
        if (this.dialog == null) {
            LogUtil.e("no dialog exist");
            return;
        }
        this.dialog.start();
    }

    public void dialogDismiss(String hint, boolean isSuccess) {
        if (this.dialog == null) {
            LogUtil.e("no dialog exist");
            return;
        }
        if (isSuccess)
            this.dialog.success(hint);
        else
            this.dialog.failure(hint);
    }

    public static abstract interface NetDialogInterface {
        public abstract void start();

        public abstract void success(String paramString);

        public abstract void failure(String paramString);
    }
}