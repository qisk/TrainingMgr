package com.blackcandle.addtraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.blackcandle.annotation.BRouter;
import com.blackcandle.baselib.event.LiveDataBus;

@BRouter("addtraining/main")
public class AddTrainingMainActivity extends AppCompatActivity {
    private final String TAG = "AddTrainingMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtraining_activity_main);
        setTitle(R.string.addtraining_title);

        LiveDataBus.BusMutableLiveData<String> liveData = LiveDataBus.getInstance().with("app:data1", String.class);
        liveData.observe(AddTrainingMainActivity.this, false, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                Log.d(TAG, "recv event data" + s);
            }
        });
    }
}
