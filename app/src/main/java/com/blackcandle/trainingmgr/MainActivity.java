package com.blackcandle.trainingmgr;

import android.os.Bundle;

import com.blackcandle.annotation.BRouter;
import com.blackcandle.baselib.event.LiveDataBus;
import com.blackcandle.baselib.router.Router;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

@BRouter("app/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 发送事件1
                MutableLiveData<String> liveData = LiveDataBus.getInstance().with("app:data1",String.class);
                liveData.postValue("app:MainActivity post data1");

                // 路由跳转
                Router.getInstance().jumpActivity("addtraining/main", null);
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                 */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // 发送事件2
            MutableLiveData<String> liveData = LiveDataBus.getInstance().with("app:data2",String.class);
            liveData.postValue("app:MainActivity post data2");

            // 路由跳转
            Router.getInstance().jumpActivity("settings/main", null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}