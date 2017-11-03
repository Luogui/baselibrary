package com.android.luogui.baselibrary.netWork.retrofit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 描述： 获取泛型参数类型
 * Created by LuoGui on 2017/3/10.
 */

public class ParamType implements ParameterizedType {
    private Type type;

    public ParamType(Type type) {
        this.type = type;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] {type};
    }

    @Override
    public Type getRawType() {
        return ArrayList.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
