package com.blackcandle.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blackcandle.annotation.BRouter;

@BRouter("settings/main")
public class SettingsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
