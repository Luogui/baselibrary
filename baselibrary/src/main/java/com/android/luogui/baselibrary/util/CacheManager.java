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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class CacheManager {

    Context context;
    private SharedPreferences sharedPreferences;
    private static String NAME = "setting";

    private static CacheManager instance = null;

    public static CacheManager getInstance(Context context) {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager(context);
                    return instance;
                }
            }
        }
        return instance;
    }

    private CacheManager(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void addCache(String name, byte[] bytes) {
        try {
            File file = new File(this.context.getCacheDir(), name);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            LogUtil.t(e);
        }
    }

    public File getCache(String name) {
        File file = new File(this.context.getCacheDir(), name);
        if (!file.exists()) {
            return null;
        }
        return file;
    }

    public File getCacheFile(String name) {
        File file = new File(this.context.getCacheDir(), name);
        if (!file.exists()) {
            return null;
        }
        return file;
    }

    public CacheManager savePrimitiveType(String key, Object value) {
        Map map = new HashMap();
        map.put(key, value);
        return savePrimitiveType(map);
    }

    public CacheManager savePrimitiveType(Map<String, Object> map) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();

        for (String key : map.keySet()) {
            Object value = map.get(key);
            if ((value instanceof String))
                editor.putString(key, (String) value);
            else if ((value instanceof Integer))
                editor.putInt(key, ((Integer) value).intValue());
            else if ((value instanceof Long))
                editor.putLong(key, ((Long) value).longValue());
            else if ((value instanceof Boolean))
                editor.putBoolean(key, ((Boolean) value).booleanValue());
            else if ((value instanceof Float))
                editor.putFloat(key, ((Float) value).floatValue());
            else {
                new Throwable("错误的类型!");
            }
        }
        editor.commit();
        return instance;
    }

    public CacheManager savePrimitiveSerializable(String key, Serializable value) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        try {
            editor.putString(key, serialize(value));
            editor.commit();
        } catch (IOException e) {
            LogUtil.e("序列化失败");
        }
        return instance;
    }

    private String serialize(Serializable serializable)
            throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(serializable);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    private Serializable deSerialization(String str)
            throws IOException, ClassNotFoundException {
        String redStr = URLDecoder.decode(str, "UTF-8");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr
                .getBytes("ISO-8859-1"));

        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        Serializable serializable = (Serializable) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return serializable;
    }

    public void saveFile(String name, byte[] bytes)
            throws IOException {
        File file = new File(this.context.getCacheDir(), name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    public int getInt(String key) {
        return this.sharedPreferences.getInt(key, 0);
    }

    public long getLong(String key) {
        return this.sharedPreferences.getLong(key, 0L);
    }

    public boolean getBoolean(String key) {
        return this.sharedPreferences.getBoolean(key, false);
    }

    public String getString(String key) {
        return this.sharedPreferences.getString(key, "");
    }

    public float getFloat(String key) {
        return this.sharedPreferences.getFloat(key, 0.0F);
    }

    public Serializable getSerializable(String key) {
        try {
            String cont = this.sharedPreferences.getString(key, "");
            if (cont.equals("")) {
                return null;
            }
            return deSerialization(this.sharedPreferences.getString(key, ""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public void clearPrimitiveType() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void cleanInsideCache() {
        deleteFolderFile(this.context.getCacheDir().getAbsolutePath(), false);
    }

    public void cleanFiles() {
        deleteFolderFile(this.context.getFilesDir().getAbsolutePath(), false);
    }

    public void cleanExternalCache() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            deleteFolderFile(this.context.getExternalCacheDir().getAbsolutePath(), false);
        }
    }

    public static long getFolderSize(File file)
            throws Exception {
        long size = 0L;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory())
                    size += getFolderSize(fileList[i]);
                else
                    size += fileList[i].length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath))
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else if (file.listFiles().length == 0)
                        file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static String getFormatSize(double size) {
        double kiloByte = size / 1024.0D;
        if (kiloByte < 1.0D) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024.0D;
        if (megaByte < 1.0D) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, 4)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024.0D;
        if (gigaByte < 1.0D) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, 4)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024.0D;
        if (teraBytes < 1.0D) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, 4)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, 4).toPlainString() + "TB";
    }

    public String getCacheSize(File file)
            throws Exception {
        return getFormatSize(getFolderSize(file));
    }

    public String getAllCacheSize()
            throws Exception {
        long size = getFolderSize(this.context.getCacheDir()) +
                getFolderSize(this.context
                        .getCacheDir()) +
                getFolderSize(this.context
                        .getCacheDir());
        return getFormatSize(size);
    }

    public void clearAllCache() {
        cleanExternalCache();
        cleanFiles();
        cleanInsideCache();
    }
}
