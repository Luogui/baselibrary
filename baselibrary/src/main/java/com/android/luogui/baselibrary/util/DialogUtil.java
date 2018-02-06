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

package com.android.luogui.baselibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.ui.TipDialog;



/**
 * Created by LG on 2017/1/6.
 */

public class DialogUtil {


    /**
     * showTip 确定 取消
     *
     * @param context      context
     * @param hint         hint
     * @param dialogResult result
     */
    public static void showTip(Context context, String title, String hint, final DialogResult dialogResult) {
        TipDialog.Builder builder = new TipDialog.Builder(context);
        builder.setMessage(hint);
        builder.setBtnCancelColor(R.color.gray_bg);
        builder.setTitleColor(R.color.colorPrimary);
        builder.setMessageTextColor(R.color.black_text);
        builder.setBtnCancelTextColor(R.color.gray_text);
        builder.setBtnSureColor(R.color.colorPrimary);
        builder.setTitle(StringUtil.isEmpty(title) ? "提示" : title);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogResult.result("1");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogResult.result("0");
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    /**
     * 底部弹窗
     *
     * @param context activity
     * @param view    自定义布局
     * @param title   标题
     */
    public static Dialog showBottomDialog(Activity context, View view, String title) {
        final Dialog bottomDialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_base_view, null);
        TextView tvTitle = contentView.findViewById(R.id.tv_title);
        TextView tvCancel = contentView.findViewById(R.id.tv_cancel);
        LinearLayout bottomView = contentView.findViewById(R.id.bottomView);
        tvTitle.setText(title);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomView.addView(view);
        bottomDialog.setContentView(contentView);

        //设置contentView为屏幕宽度
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = ScreenUtil.getDisplayMetrics(context).widthPixels;
        contentView.setLayoutParams(layoutParams);

        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.show();
        return bottomDialog;
    }


    /**
     * margin 底部弹出
     *
     * @param context     activity
     * @param view        自定义布局
     * @param title       标题
     * @param width       两侧margin
     * @param bottomWidth 底部margin
     */
    public static void showMarginBottomDialog(Activity context, View view, String title, float width, float bottomWidth) {
        final Dialog bottomDialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_base_view, null);
        TextView tvTitle = contentView.findViewById(R.id.tv_title);
        TextView tvCancel = contentView.findViewById(R.id.tv_cancel);
        LinearLayout bottomView = contentView.findViewById(R.id.bottomView);
        tvTitle.setText(title);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomView.addView(view);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = ScreenUtil.getDisplayMetrics(context).widthPixels - ScreenUtil.dip2px(width);
        params.bottomMargin = ScreenUtil.dip2px(bottomWidth);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.show();
    }

    public interface DialogResult {
        void result(String s);
    }

}
