/*
 * Copyright (c) 2018.
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

package com.android.luogui.baseproject.down;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.luogui.baselibrary.MyApplication;
import com.android.luogui.baseproject.R;
import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;

import java.io.File;

public class DownActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String TAG = "===============";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);

        recyclerView = findViewById(R.id.recycler);
        Aria.download(this).register();

    }


    @Download.onWait
    void onWait(DownloadTask task) {
        Log.d(TAG, "wait ==> " + task.getDownloadEntity().getFileName());
    }

    @Download.onPre
    protected void onPre(DownloadTask task) {
        Log.d(TAG, "onPre");
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        Log.d(TAG, "onStart");
    }

    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        Log.d(TAG, "running");
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        Log.d(TAG, "resume");
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        Log.d(TAG, "stop");
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        Log.d(TAG, "cancel");
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        Log.d(TAG, "fail");
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        Log.d(TAG, "path ==> " + task.getDownloadEntity().getDownloadPath());
//        L.d(TAG, "md5Code ==> " + CommonUtil.getFileMD5(new File(task.getDownloadPath())));
    }


}
