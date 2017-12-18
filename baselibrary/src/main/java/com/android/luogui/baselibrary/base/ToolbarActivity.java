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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.RuntimePermissions.PermissionsManager;
import com.android.luogui.baselibrary.Setting;
import com.android.luogui.baselibrary.constant.BroadcastConstant;


public class ToolbarActivity<T> extends BaseActivity {
    private ViewGroup.LayoutParams LAYOUT_PARAMS = new ViewGroup.LayoutParams(-1, -1);
    Toolbar toolbar;
    TextView toolbar_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
    }

    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(layoutResID, true);
    }

    public void setContentView(@LayoutRes int layoutResID, boolean hasToolbar) {
        if (hasToolbar) {
            LinearLayout view = ((LinearLayout) LayoutInflater.from(this.context).inflate(R.layout.base_layout, null, false));
            this.toolbar = ((Toolbar) view.findViewById(R.id.toolbar));
            this.toolbar.setTitle("");
            this.toolbar_title = ((TextView) view.findViewById(R.id.toobar_title));
            setSupportActionBar(this.toolbar);
            ViewGroup base_vg = (ViewGroup) view.findViewById(R.id.base_content);
            base_vg.addView(getLayoutInflater().inflate(layoutResID, null), this.LAYOUT_PARAMS);
            super.setContentView(view);
        } else {
            super.setContentView(layoutResID);
        }
    }

    protected void setToolbarBackBtn() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setTitle(CharSequence title) {
        this.toolbar_title.setText(title);
        this.toolbar_title.setTextColor(Setting.titleColor);
    }

    public void setTitle(@StringRes int title) {
        setTitle(getString(title));
    }

    public void setTitle(@StringRes int title, boolean hasBack) {
        setTitle(getString(title), hasBack);
    }

    public void setTitle(CharSequence title, boolean hasBack) {
        setTitle(title);
        if (hasBack) {
            setToolbarBackBtn();
            if (Setting.navigationIcon != 0)
                setIcon(Setting.navigationIcon);
        }
    }

    protected Toolbar getToolbar() {
        return this.toolbar;
    }

    public TextView getToobar_title() {
        return this.toolbar_title;
    }

    protected void setIcon(@DrawableRes int resId) {
        this.toolbar.setNavigationIcon(resId);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(BroadcastConstant.ACTIVITY_FINISH);
        registerReceiver(receiver, filter);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}