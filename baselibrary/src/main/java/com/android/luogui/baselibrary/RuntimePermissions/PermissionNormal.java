package com.android.luogui.baselibrary.RuntimePermissions;

import android.Manifest;

/**
 * 描述： 权限
 * Created by LuoGui on 2017/2/21.
 */

public class PermissionNormal {

    public static final String[] ReadWrite = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    //定位权限
    public static final String[] LOCATION = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};




    public static String[] InitApplication = ReadWrite;
}
