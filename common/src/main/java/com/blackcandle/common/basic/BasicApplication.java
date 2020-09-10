package com.blackcandle.common.basic;

import android.app.Application;
import com.blackcandle.common.BuildConfig;

public class BasicApplication extends Application {
    //
    final static boolean is_application = BuildConfig.is_application;

    @Override
    public void onCreate() {
        super.onCreate();
        // 在这里处理每个组件都要进行的初始化操作
    }
}
