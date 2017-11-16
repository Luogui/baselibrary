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