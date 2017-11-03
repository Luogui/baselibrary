package com.android.luogui.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.luogui.baselibrary.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class LogUtil {

    public static final int NON_LOG = 0;
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    private static int level = 6;
    private static String TAG = "*========Log========*";
    private static String fileName = "LogInfo";
    public static Boolean PRINT = false;

    public static void v(String msg) {
        v(null, msg);
    }

    public static void v(String Tag, String msg) {
        if (level >= 1) {
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            String tag = getTagInfo(stackTraceElement);
            if (Tag == null)
                Log.v(TAG, msg + "     " + tag);
            else {
                Log.v(TAG + "-" + Tag, msg + "     " + tag);
            }
            if (PRINT)
                print(tag + "&" + msg);
        }
    }

    public static void d(String msg) {
        d(null, msg);
    }

    public static void d(String format, Object[] objects) {
        d(null, String.format(format, objects));
    }

    public static void d(String Tag, String msg) {
        if (level >= 2) {
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            String tag = getTagInfo(stackTraceElement);
            if (Tag == null)
                Log.d(TAG, msg + "     " + tag);
            else {
                Log.d(TAG + "-" + Tag, new StringBuilder().append(msg).append("     ").append(tag).toString());
            }
            if (PRINT)
                print(tag + "&" + msg);
        }
    }

    public static void i(String msg) {
        i(null, msg);
    }

    public static void i(String Tag, String msg) {

        if (!PRINT) return;

        if (level >= 4) {
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            String tag = getTagInfo(stackTraceElement);
            if (Tag == null)
                Log.i(TAG, msg + "     " + tag);
            else {
                Log.i(TAG + "-" + Tag, msg + "     " + tag);
            }
            if (PRINT)
                print(tag + "&" + msg);
        }
    }

    public static void w(String msg) {
        w(null, msg);
    }

    public static void w(String Tag, String msg) {
        if (level >= 5) {
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            String tag = getTagInfo(stackTraceElement);
            if (Tag == null)
                Log.w(TAG, new StringBuilder().append(msg).append("     ").append(tag).toString());
            else {
                Log.w(new StringBuilder().append(TAG).append("-").append(Tag).toString(), new StringBuilder().append(msg).append("     ").append(tag).toString());
            }
            if (PRINT.booleanValue())
                print(new StringBuilder().append(tag).append("&").append(msg).toString());
        }
    }

    public static void e(String msg) {
        e(null, msg);
    }

    public static void e(String Tag, String msg) {
        if (level >= 6) {
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            String tag = getTagInfo(stackTraceElement);
            if (Tag == null)
                Log.e(TAG, new StringBuilder().append(msg).append("     ").append(tag).toString());
            else {
                Log.e(new StringBuilder().append(TAG).append("-").append(Tag).toString(), new StringBuilder().append(msg).append("     ").append(tag).toString());
            }
            if (PRINT.booleanValue())
                print(new StringBuilder().append(tag).append("&").append(msg).toString());
        }
    }

    public static void t(Throwable tr) {
        t(null, "", tr);
    }

    public static void t(String msg, Throwable tr) {
        t(null, msg, tr);
    }

    public static void t(String Tag, String msg, Throwable tr) {
        if (level >= 6) {
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            String tag = getTagInfo(stackTraceElement);
            if (Tag == null)
                Log.e(TAG, new StringBuilder().append(msg).append("     ").append(tag).toString(), tr);
            else {
                Log.e(new StringBuilder().append(TAG).append("-").append(Tag).toString(), new StringBuilder().append(msg).append("     ").append(tag).toString(), tr);
            }
            if (PRINT.booleanValue())
                print(new StringBuilder().append(tag).append("&").append(msg).toString(), tr);
        }
    }

    public static void json(String json) {
        d(jsonToLog(json));
    }

    public static void list(List<Object> list) {
        d(listToLog(list));
    }

    public static String jsonToLog(String json) {
        if (TextUtils.isEmpty(json))
            return "JSON{json is null}";
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                json = jsonObject.toString(4);
            } else if (json.startsWith("[")) {
                JSONArray array = new JSONArray(json);
                json = array.toString(4);
            }
        } catch (JSONException e) {
            return json;
        }
        return json;
    }

    public static void toast(Context context, String msg) {
        toast(null, context, msg);
    }

    public static void toast(String msg){
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String format, Object[] objects) {
        toast(null, context, String.format(format, objects));
    }

    public static void toast(String Tag, final Context context, String msg) {
        if (Looper.myLooper() == Looper.getMainLooper())
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        else if ((context instanceof Activity)) {
//            ((Activity) context).runOnUiThread(new Runnable(context, msg) {
//                public void run() {
//                    Toast.makeText(this.val$content, this.val$msg, 0).show();
//                }
//            });
        }
        else e("在非主线程中输出toast");

        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        String tag = getTagInfo(stackTraceElement);
        Log.i(new StringBuilder().append(TAG).append("-").append(Tag).toString(), new StringBuilder().append(msg).append("     ").append(tag).toString());
        if (PRINT.booleanValue())
            print(new StringBuilder().append("Toast:").append(tag).append("&").append(msg).toString());
    }

    public static void toast(Context context, int resurceId) {
        String msg = context.getResources().getString(resurceId);
        toast(context, msg);
    }

    public static void toast(String Tag, Context context, int resurceId) {
        String msg = context.getResources().getString(resurceId);
        toast(Tag, context, msg);
    }

    public static String listToLog(List<Object> list) {
        StringBuilder builder = new StringBuilder();
        builder.append(new StringBuilder().append(list.getClass().getSimpleName()).append(",size = ").append(list.size()).append("\n").toString());
        for (int i = 0; i < list.size(); i++) {
            builder.append(new StringBuilder().append(i).append("  ").append(list.get(i).toString()).append("\n").toString());
        }
        return builder.toString();
    }

    public static void setLevel(int level) {
        level = level;
    }

    public static void setTAG(String tAG) {
        TAG = tAG;
    }

    public static void setFilemane(String filemane) {
        filemane = filemane;
    }

    private static String getTagInfo(StackTraceElement[] stackTraceElement) {
        StringBuilder builder = new StringBuilder();
        for (int i = 3; i < stackTraceElement.length; i++) {
            String tag = "%s.%s(%s:%d)";
            String callerClazzName = stackTraceElement[i].getClassName();

            if (callerClazzName
                    .equals(LogUtil.class
                            .getName())) {
                continue;
            }
            if ((callerClazzName.startsWith("android")) || (callerClazzName.startsWith("java"))) {
                break;
            }
            callerClazzName = callerClazzName.substring(callerClazzName
                    .lastIndexOf(".") +
                    1);
            tag = String.format(tag, new Object[]{callerClazzName, stackTraceElement[i].getMethodName(), stackTraceElement[i]
                    .getFileName(),
                    Integer.valueOf(stackTraceElement[i]
                            .getLineNumber())});
            builder.append(tag);
            builder.append("\n");
        }

        return builder.toString();
    }

    private static void print(String msg) {
        Date currentTime = new Date();
        String currentTimeStamp = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(currentTime);

        String filename = new StringBuilder().append(fileName).append(".txt").toString();
        String localPath = new StringBuilder().append(Environment.getExternalStorageDirectory().getPath()).append("/DEBUG").append(fileName).toString();
        File file1 = new File(localPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String msagement = new StringBuilder().append(currentTimeStamp).append("&").append(msg).append("\n").toString();
        try {
            BufferedWriter bos = new BufferedWriter(new FileWriter(new StringBuilder().append(localPath).append("/").append(filename).toString()));

            bos.write(msagement);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void print(String msg, Throwable tr) {
        print(new StringBuilder().append(msg).append("&Throwable:").append(tr.getMessage()).toString());
    }

    public static void setPRINT(Boolean PRINT) {
        PRINT = PRINT;
    }
}