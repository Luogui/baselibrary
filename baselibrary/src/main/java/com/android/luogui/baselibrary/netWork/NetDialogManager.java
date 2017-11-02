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