package com.android.luogui.baselibrary;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class Setting {

    public static String RSAPrivateKey = "";
    public static String RSAPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwLe0KOSRLn4szlKtoYZ8dZlNT3OTL2Ym4+yBSZ+vznTvs3lIONOc4pTtxP4Lzey00TrFDAJeJA6DmvLKqAA9roj1oW8LuRaRb5VNV8/LjecFrIoz0YFG8oqBGjI7PlpQkF2mxfhEmYFd1O+97yjc6sknV8acsrxVHhQ8l2fdFGQIDAQAB";
    public static final byte[] AES_IV = "0987654321FEDCBA".getBytes();


    @DrawableRes
    public static int navigationIcon = 0;

    @ColorInt
    public static int titleColor = -1;

}