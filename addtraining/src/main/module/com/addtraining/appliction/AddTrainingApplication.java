package com.addtraining.appliction;

import android.util.Log;

import com.blackcandle.common.basic.BasicApplication;

public class AddTrainingApplication extends BasicApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // 当组件为Application时，做组件自己的初始化动作
        Log.d("Application", "AddTrainingApplication onCreate");
    }
}
