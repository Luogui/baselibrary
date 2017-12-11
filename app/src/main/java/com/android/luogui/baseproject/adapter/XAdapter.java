package com.android.luogui.baseproject.adapter;

import android.content.Context;

import com.android.luogui.baselibrary.base.adapter.BaseRecyclerAdapter;
import com.android.luogui.baseproject.bean.NewsBean;

import java.util.List;

/**
 * describe
 * Created by  LuoGui on 2017/11/3.
 */

public class XAdapter extends BaseRecyclerAdapter<NewsBean> {


    public XAdapter(Context context, List<NewsBean> mDataList) {
        super(context, mDataList);
    }

}
