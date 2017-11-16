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

package com.android.luogui.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.Setting;

/**
 * 描述：
 * Created by LuoGui on 2017/8/28.
 */

public class ToolbarFragment extends Fragment
{
    Toolbar toolbar;
    TextView toolbar_title;

    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.base_layout, container, false);
        this.toolbar = ((Toolbar)view.findViewById(R.id.toolbar));
        this.toolbar.setTitle("");
        this.toolbar_title = ((TextView)view.findViewById(R.id.toobar_title));
        ((AppCompatActivity)getActivity()).setSupportActionBar(this.toolbar);
        setHasOptionsMenu(true);
        ViewGroup base_vg = (ViewGroup)view.findViewById(R.id.base_content);
        initView(inflater, base_vg);
        return view;
    }

    protected void initView(LayoutInflater inflater, ViewGroup container)
    {
    }

    protected void setToolbarBackBtn() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void setTitle(String title) {
        this.toolbar_title.setText(title);
        this.toolbar_title.setTextColor(Setting.titleColor);
    }
    public void setTitle(@StringRes int title) {
        this.toolbar_title.setText(getString(title));
        this.toolbar_title.setTextColor(Setting.titleColor);
    }

    public void setTitle(@StringRes int title, boolean hasBack) {
        setTitle(title);
        if (hasBack) {
            setToolbarBackBtn();
            if (Setting.navigationIcon != 0)
                setIcon(Setting.navigationIcon);
        }
    }

    protected Toolbar getToolbar()
    {
        return this.toolbar;
    }

    protected void setIcon(@DrawableRes int resId) {
        this.toolbar.setNavigationIcon(resId);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.clear();
    }
}