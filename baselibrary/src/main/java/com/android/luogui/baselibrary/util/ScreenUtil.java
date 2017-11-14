package com.android.luogui.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.android.luogui.baselibrary.MyApplication;

import java.lang.reflect.Field;

/**
 * 屏幕
 * Created by LG on 2017/1/2.
 */

public class ScreenUtil {

    /**
     * 屏幕宽高
     */
    private static int[] getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = MyApplication.getInstance().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        return new int[] { screenWidth, screenHeight };
    }

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * @Title: getStatusBarHeight
     * @Description: 获取状态栏高度
     * @param context
     * @return int
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * @Title: getScreenWidth @Description: 获取手机屏幕的宽度 @param @return
     * 设定文件 @return int 返回类型 @throws
     */
    public static int getScreenWidth() {
        int screen[] = getScreenSize();
        return screen[0];
    }

    /**
     * @Title: getScreenHeight @Description: 获取手机屏幕的高度 @param @return
     * 设定文件 @return int 返回类型 @throws
     */
    public static int getScreenHeight() {
        int screen[] = getScreenSize();
        return screen[1];
    }

    /**
     * 根据手机分辨率将dp转为px单位
     */
    public static int dip2px(float dpValue) {
        final float scale = MyApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = MyApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * px -> sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp -> px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
