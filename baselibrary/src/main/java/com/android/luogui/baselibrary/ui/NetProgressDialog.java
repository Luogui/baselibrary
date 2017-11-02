package com.android.luogui.baselibrary.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.mInterface.OnFinishResultListener;
import com.android.luogui.baselibrary.netWork.NetDialogManager;


/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class NetProgressDialog extends Dialog implements NetDialogManager.NetDialogInterface {

    NetProgressBar progress_bar;
    TextView progress_text;
    ImageView progress_result;
    Handler dismissHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Activity activity = NetProgressDialog.this.getOwnerActivity();

            if ((activity == null) || (activity.isFinishing())) {
                return true;
            }
            NetProgressDialog.this.dismiss();
            return true;
        }
    });

    public NetProgressDialog(Context context) {
        super(context, R.style.loading_dialog);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.base_progress_dialog, null);
        setContentView(view, new ViewGroup.LayoutParams(-1, -2));

        this.progress_bar = ((NetProgressBar) view.findViewById(R.id.progress_bar));
        this.progress_text = ((TextView) view.findViewById(R.id.progress_text));
        this.progress_result = ((ImageView) view.findViewById(R.id.progress_result));

        this.progress_bar.setFinishResultListener(new OnFinishResultListener() {
            public void onFinish(boolean status, Object result) {
                NetProgressDialog.this.dismissHandler.sendEmptyMessageDelayed(1, 80L);
            }
        });
    }

    public void start() {
        start(null);
    }

    public void start(String hint) {
        show();
        this.progress_text.setText(hint == null ? "   请等待..." : hint);
        this.progress_bar.setVisibility(0);
        this.progress_result.setVisibility(8);
    }

    public void success(String hint) {
        this.progress_bar.setResult(true);

        this.progress_text.setText(hint == null ? "" : hint);
    }

    public void failure(String hint) {
        this.progress_bar.setResult(false);

        this.progress_text.setText(hint == null ? "" : hint);
    }
}