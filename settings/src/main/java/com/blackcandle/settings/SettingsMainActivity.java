package com.blackcandle.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.blackcandle.annotation.BRouter;
import com.blackcandle.baselib.event.LiveDataBus;

@BRouter("settings/main")
public class SettingsMainActivity extends AppCompatActivity {
    private final String TAG = "SettingsMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity_main);
        setTitle(R.string.settings_title);

        LiveDataBus.BusMutableLiveData<String> liveData = LiveDataBus.getInstance().with("app:data2", String.class);
        liveData.observe(SettingsMainActivity.this, false, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                Log.d(TAG, "recv event data" + s);
            }
        });
    }
}
