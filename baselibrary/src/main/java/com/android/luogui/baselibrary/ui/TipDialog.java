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

package com.android.luogui.baselibrary.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;


/**
 * @author LG
 */
public class TipDialog extends Dialog {

    public TipDialog(Context context, int theme) {
        super(context, theme);
    }

    public TipDialog(Context context) {
        super(context);
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(true);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {

        private Context context;
        private String title;  //标题
        private String message; //信息
        private String positiveButtonText;   //确定
        private String negativeButtonText;    //取消
        private View contentView;    //自定义view
        private int titleColor ;  //标题颜色
        private int btnSureColor;  //确认按键颜色
        private int btnCancelColor;  //取消按键颜色
        private int btnSureTextColor;  //确认按键字体颜色
        private int btnCancelTextColor;  //取消按键的颜色
        private int containViewBackgroundColor; //contentView背景颜色
        private int contentViewBackgroundColor; //总体背景颜色
        private int btnSureVisibility = View.GONE;  //确认按键  默认不可见
        private int btnCancelVisibility = View.GONE;  //取消按键  默认不可见
        private int messageTextColor;   //消息颜色
        private float messageTextSize;    //消息文字大小
        private TipDialog dialog;
        private int style;


        private OnClickListener positiveButtonClickListener, negativeButtonClickListener;

        public Builder(Context context, @StyleRes int style) {
            this.context = context;
            this.style = style;
        }

        public Builder(Context context) {
            this.context = context;
            this.style =  R.style.TipDialog;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }


        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            btnSureVisibility = View.VISIBLE;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            btnCancelVisibility = View.VISIBLE;
            return this;
        }


        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setContentViewBackground(int contentViewBackgroundColor){
            this.contentViewBackgroundColor = contentViewBackgroundColor;
            return this;
        }
        public Builder setContainViewBackground(int containViewBackgroundColor){
            this.containViewBackgroundColor = containViewBackgroundColor;
            return this;
        }

        public Builder setBtnSureColor(int btnSureColor) {
            this.btnSureColor = btnSureColor;
            return this;
        }

        public Builder setBtnCancelColor(int btnCancelColor) {
            this.btnCancelColor = btnCancelColor;
            return this;
        }

        public Builder setBtnSureTextColor(int btnSureTextColor) {
            this.btnSureTextColor = btnSureTextColor;
            return this;
        }

        public Builder setBtnCancelTextColor(int btnCancelTextColor) {
            this.btnCancelTextColor = btnCancelTextColor;
            return this;
        }

        public Builder setBtnSureVisibility(int btnSureVisibility) {
            this.btnSureVisibility = btnSureVisibility;
            return this;
        }

        public Builder setBtnCancelVisibility(int btnCancelVisibility) {
            this.btnCancelVisibility = btnCancelVisibility;
            return this;
        }

        public Builder setMessageTextColor(int messageTextColor) {
            this.messageTextColor = messageTextColor;
            return this;
        }

        public Builder setMessageTextSize(float messageTextSize) {
            this.messageTextSize = messageTextSize;
            return this;
        }

        public void dissmiss(){
            if (dialog!=null)
            dialog.dismiss();
        }

        public TipDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new TipDialog(context,style);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.dialog_tip, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            TextView tvTitle = (TextView) layout.findViewById(R.id.dialog_title);
            Button btnCancel = (Button) layout.findViewById(R.id.positiveButton);
            Button btnSure = (Button) layout.findViewById(R.id.negativeButton);
            View vLine = layout.findViewById(R.id.dialog_line);
            TextView tvMessage = (TextView) layout.findViewById(R.id.dialog_message);
            if (title==null|| title.isEmpty()){
                tvTitle.setVisibility(View.GONE);
                vLine.setVisibility(View.GONE);
            }else {
                tvTitle.setText(title);
            }
            if (titleColor!=0){
                vLine.setBackgroundColor(ContextCompat.getColor(context,titleColor));
                tvTitle.setTextColor(ContextCompat.getColor(context,titleColor));
            }
            if (positiveButtonText != null) {
                btnSure.setText(positiveButtonText);
                if (btnSureColor!=0) btnSure.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
                if (btnSureTextColor!=0) btnSure.setTextColor(ContextCompat.getColor(context,btnSureTextColor));
                btnSure.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (positiveButtonClickListener != null) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                        dialog.dismiss();
                    }
                });
            }
            if (negativeButtonText != null) {
                btnCancel.setText(negativeButtonText);
                if (btnCancelColor!=0) btnCancel.setBackgroundColor(ContextCompat.getColor(context,btnCancelColor));
                if (btnCancelTextColor!=0) btnCancel.setTextColor(ContextCompat.getColor(context,btnCancelTextColor));
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (negativeButtonClickListener != null) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                        dialog.dismiss();
                    }
                });
            }

            if (containViewBackgroundColor!=0) {
                layout.findViewById(R.id.dialog_contain).setBackgroundColor(containViewBackgroundColor);
            }

            if (contentViewBackgroundColor!=0) {
                layout.findViewById(R.id.dialog_content).setBackgroundColor(contentViewBackgroundColor);
            }

            if (message != null) {
                tvMessage.setText(message);
                if (messageTextColor!=0) tvMessage.setTextColor(ContextCompat.getColor(context, messageTextColor));
                if (messageTextSize!=0) tvMessage.setTextSize(messageTextSize);
            } else if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.dialog_contain)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.dialog_contain)).addView(contentView);
            }



            btnSure.setVisibility(btnSureVisibility);
            btnCancel.setVisibility(btnCancelVisibility);
            dialog.setContentView(layout);
            return dialog;
        }

    }
}
