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

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.android.luogui.baselibrary.Setting;
import com.android.luogui.baselibrary.constant.Constant;
import com.android.luogui.baselibrary.util.LogUtil;
import com.android.luogui.baselibrary.util.StringUtil;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描述： AES加密
 * Created by LuoGui on 2017/8/28.
 */

public class AES {

    public static String decrypt(String key, String encrypted) {
        if (TextUtils.isEmpty(encrypted))
            return encrypted;
        try {
            byte[] enc = Base64.decode(encrypted.getBytes("UTF-8"), 0);

            byte[] result = decrypt(key, enc);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            LogUtil.t(e);
        }
        return null;
    }

    public static String decryptString(String encrypted) {
        if (TextUtils.isEmpty(encrypted))
            return encrypted;
        try {
            byte[] enc = Base64.decode(encrypted.getBytes("UTF-8"), 0);

            byte[] result = decrypt(Constant.AES_KEY, enc);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            LogUtil.t(e);
        }
        return null;
    }

    private static byte[] decrypt(String key, byte[] encrypted)
            throws Exception {
        byte[] raw = getSecret(key.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, skeySpec, new IvParameterSpec(Setting.AES_IV));
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static Map<String, String> encrypt(String cleartext)
            throws Exception {
        Map map = new HashMap();
        if (TextUtils.isEmpty(cleartext)) {
            return map;
        }
        byte[] send = getRawKey(generateKey().getBytes("UTF-8"));
        byte[] key = getSecret(send);

        map.put("key", new String(send, "UTF-8"));
        try {
            byte[] result = encrypt(key, cleartext.getBytes("UTF-8"));
            String s = Base64.encodeToString(result, 0);
            map.put("data", s);
            return map;
        } catch (Exception e) {
            LogUtil.t(e);
        }
        return null;
    }

    public static String encryptString(String cleartext) {
        if (TextUtils.isEmpty(cleartext)) return "";
        byte[] send = Constant.AES_KEY.getBytes();
        byte[] key = getSecret(send);
        try {
            byte[] result = encrypt(key, cleartext.getBytes("UTF-8"));
            return Base64.encodeToString(result, 0);
        } catch (Exception e) {
            LogUtil.t(e);
        }
        return "";
    }

    private static byte[] encrypt(byte[] raw, byte[] clear)
            throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, keySpec, new IvParameterSpec(Setting.AES_IV));
            return cipher.doFinal(clear);
        }catch (Exception E){
            Log.i("Log", E.toString());
        }
        return null;
    }

    /**
     *  // 对密钥进行处理
     * @param seed
     * @return
     * @throws Exception
     */
    private static byte[] getRawKey(byte[] seed)
            throws Exception {
        return StringUtil.getRandomString(16).getBytes();
    }

    public static byte[] getSecret(byte[] raw) {
        byte[] bs = Arrays.copyOf(raw, 16);
        bs[14] = raw[0];
        bs[15] = raw[1];
        return bs;
    }

    /**
     *   生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
     *  在4.2以上版本中，SecureRandom获取方式发生了改变
     * @return
     */
    private static String generateKey() {
        try {
            SecureRandom localSecureRandom;
            if (Build.VERSION.SDK_INT >= 17)
                localSecureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            else {
                localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
            }

            byte[] bytes_key = new byte[20];
            localSecureRandom.nextBytes(bytes_key);
            return StringUtil.bytesToHexString(bytes_key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}