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

package com.android.luogui.baselibrary.decryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * describe
 * Created by  LuoGui on 2017/10/9.
 */

public class MD5 {

    public static String encrypt(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException("Huh, MD5 should be supported?", var7);
        } catch (UnsupportedEncodingException var8) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", var8);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        byte[] var3 = hash;
        int var4 = hash.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            if((b & 255) < 16) {
                hex.append("0");
            }

            hex.append(Integer.toHexString(b & 255));
        }

        return hex.toString();
    }

    public static boolean judgeIsEmpty(String... arg) {
        String[] var1 = arg;
        int var2 = arg.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String i = var1[var3];
            if(null == i || i.trim().equals("")) {
                return true;
            }
        }

        return false;
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "[^a-zA-Z0-9一-龥]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 计算文件的MD5值
     * @param file file
     * @return str
     */
    public static String md5(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream in = null;
        String result = "";
        byte buffer[] = new byte[8192];
        int len;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            byte[] bytes = md5.digest();

            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null!=in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
