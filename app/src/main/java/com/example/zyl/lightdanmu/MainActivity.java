package com.example.zyl.lightdanmu;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lightdanmu.DanMu;
import com.example.lightdanmu.DanMuSurfaceView;

public class MainActivity extends AppCompatActivity {

    Handler handler=new Handler();
    int i = 0;
    Runnable runnable=new Runnable(){
            @Override
            public void run() {
                danmu.addDanMu(new DanMu(i+++"aaaa"));
                handler.postDelayed(this, 100);
            }
    };
    DanMuSurfaceView danmu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        danmu = findViewById(R.id.danmu);


        handler.postDelayed(runnable, 2000);
    }
}
