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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class NetWorkUtil {

    public static void installApk(Context context, File apkfile) {
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent("android.intent.action.VIEW");
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        context.startActivity(i);
    }

    public static File downloadApk(String path, ProgressDialog pb)
            throws Exception {
        int po = path.lastIndexOf("/");
        String fileName = path.substring(po + 1);
        String file_dir = getFilePath();
        File dir = new File(file_dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(file_dir + fileName);

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            int max = conn.getContentLength();

            pb.setMax(max);
            int count = 0;
            InputStream is = conn.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);

                count += len;
                pb.setProgress(count);
            }
            is.close();
            fos.close();
        }
        return file;
    }

    private static String getFilePath() {
        String file_dir = "";
        boolean isRootDirExist = Environment.getExternalStorageDirectory().exists();
        if (isRootDirExist)
            file_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/apk/";
        else {
            file_dir = "/data/data/com.android.luogui.baselibrary/files/apk/";
        }
        return file_dir;
    }
}