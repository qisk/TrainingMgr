package com.blackcandle.trainingmgr;

import android.util.Log;

import com.blackcandle.common.basic.BasicApplication;

public class MyApplication extends BasicApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // 当其他组件为Library时，这里进行项目的初始化工作
        Log.d("Application", "MyApplication onCreate");
    }
}
