package com.blackcandle.addtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blackcandle.annotation.BRouter;

@BRouter("addtraining/main")
public class AddTrainingMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtraining_activity_main);
        setTitle(R.string.addtraining_title);
    }
}
