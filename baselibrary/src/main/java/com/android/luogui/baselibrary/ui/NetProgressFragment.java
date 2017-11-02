package com.android.luogui.baselibrary.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.mInterface.OnFinishResultListener;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class NetProgressFragment extends DialogFragment
{
    NetProgressBar progress_bar;
    TextView progress_text;
    ImageView progress_result;
    String hint = null;

    boolean canCancel = true;

    Handler dismissHandler = new Handler(new Handler.Callback()
    {
        public boolean handleMessage(Message msg) {
            Activity activity = NetProgressFragment.this.getActivity();

            if ((activity == null) || (activity.isFinishing())) {
                return true;
            }
            NetProgressFragment.this.dismissAllowingStateLoss();
            return true;
        }
    });

    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.loading_dialog);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(1);
        View view = inflater.inflate(R.layout.base_progress_dialog, container, false);
        this.progress_bar = ((NetProgressBar)view.findViewById(R.id.progress_bar));
        this.progress_text = ((TextView)view.findViewById(R.id.progress_text));
        this.progress_result = ((ImageView)view.findViewById(R.id.progress_result));

        this.progress_bar.setFinishResultListener(new OnFinishResultListener()
        {
            public void onFinish(boolean status, Object result)
            {
            }
        });
        if (getDialog() != null) {
            getDialog().setCancelable(this.canCancel);
            getDialog().setCanceledOnTouchOutside(false);
        }

        return view;
    }

    public void onResume()
    {
        super.onResume();
        this.progress_text.setText(this.hint == null ? "   请等待..." : this.hint);
        this.progress_result.setVisibility(View.GONE);
    }

    public void start(FragmentManager fragmentManager, String tag)
    {
        start(fragmentManager, tag, null);
    }

    public void start(FragmentManager fragmentManager, String tag, String hint) {
        show(fragmentManager, tag);
        this.hint = hint;
    }

    public void success(String hint)
    {
        this.progress_bar.setResult(true);
        this.progress_text.setText(hint == null ? "" : hint);
    }
    public void failure(String hint) {
        this.progress_bar.setResult(false);
        this.progress_text.setText(hint == null ? "" : hint);
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
        if (getDialog() != null) {
            getDialog().setCancelable(canCancel);
            getDialog().setCanceledOnTouchOutside(canCancel);
        }
    }
}
