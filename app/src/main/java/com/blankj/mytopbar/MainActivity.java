package com.blankj.mytopbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private MyTopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopBar = (MyTopBar) findViewById(R.id.topBar);
        mTopBar.setOnTopbarClickListener(new MyTopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this,
                        "left", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this,
                        "right", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // 控制topbar上组件的状态
        mTopBar.setButtonVisable(0, true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTopBar.setButtonVisable(1, false);
            }
        }, 10000);

    }


}
