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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.luogui.baselibrary.R;
import com.android.luogui.baselibrary.mInterface.ChangeFragmentPageInterface;

import java.util.ArrayList;
import java.util.List;

public class BaseFragmentActivity extends BaseActivity implements ChangeFragmentPageInterface {

    public static final String CHANGE_PAGE = "page";
    LinearLayout bottomLayout;
    TabLayout tablayout;
    List<Fragment> fragments = new ArrayList();

    int selectedPage = 0;

    protected TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        public void onTabSelected(TabLayout.Tab tab) {
            TextView textView = (TextView) tab.getCustomView().findViewById(16908308);
            textView.setTextColor(BaseFragmentActivity.this.tablayout.getTabTextColors());
            BaseFragmentActivity.this.showFragment(tab.getPosition());
        }

        public void onTabUnselected(TabLayout.Tab tab) {
            TextView textView = (TextView) tab.getCustomView().findViewById(16908308);
            textView.setTextColor(BaseFragmentActivity.this.tablayout.getTabTextColors());
        }

        public void onTabReselected(TabLayout.Tab tab) {
            if (tab.getPosition() != BaseFragmentActivity.this.selectedPage)
                BaseFragmentActivity.this.showFragment(tab.getPosition());
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_base_fragment);
        this.bottomLayout = ((LinearLayout) findViewById(R.id.bottomlayout));
        this.tablayout = ((TabLayout) findViewById(R.id.tablayout));
        initFragment(savedInstanceState);
        if (configTabLayout(this.tablayout)) {
            this.bottomLayout.setVisibility(0);
        }
        int page = getIntent().getIntExtra("page", -1);
        if (page != -1)
            changeToPage(page);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            initFragmentList(this.fragments);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            for (int i = 0; i < this.fragments.size(); i++) {
                transaction.add(R.id.framelayout, (Fragment) this.fragments.get(i), String.valueOf(i))
                        .hide((Fragment) this.fragments
                                .get(i));
            }

            if (this.fragments.size() != 0) {
                transaction.show((Fragment) this.fragments.get(0));
            }
            transaction.commitNow();
        } else {
            for (int i = 0; i < this.fragments.size(); i++)
                this.fragments.set(i, getSupportFragmentManager().findFragmentByTag(String.valueOf(i)));
        }
    }

    protected void initFragmentList(List<Fragment> fragments) {
    }

    protected boolean configTabLayout(TabLayout tab) {
        this.tablayout.addOnTabSelectedListener(this.tabSelectedListener);
        for (int i = 0; i < this.fragments.size(); i++) {
            this.tablayout.addTab(getNewTab(i, this.tablayout), i == 0);
        }

        return true;
    }

    protected TabLayout.Tab getNewTab(int page, TabLayout tabLayout) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.base_tab_item, null);
        TabLayout.Tab tab = tabLayout.newTab().setCustomView(view);

        return tab;
    }

    private synchronized void showFragment(int position) {
        if (!allowJumpPage(position)) {
            return;
        }
        Fragment fragment = (Fragment) this.fragments.get(position);
        if (fragment.isAdded()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            for (Fragment f : this.fragments) {
                if (f.isVisible()) {
                    transaction.hide(f);
                }
            }
            transaction.show(fragment).commit();
        }
        this.selectedPage = position;
    }

    protected boolean allowJumpPage(int position) {
        return true;
    }

    public void setUnreadNum(int page, int num) {
        TextView textView = (TextView) this.tablayout.getTabAt(page)
                .getCustomView().findViewById(R.id.unread_num);
        textView.setText(num + "");
        if (num != 0)
            textView.setVisibility(0);
        else
            textView.setVisibility(8);
    }

    public void changeToPage(int page) {
        this.tablayout.getTabAt(page).select();
    }
}