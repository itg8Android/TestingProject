package com.itg.testingproject.sleep;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.itg.testingproject.R;
import com.itg.testingproject.Rolling;
import com.itg.testingproject.finaliseWidget.BreathwaveView;
import com.itg.testingproject.widget.CubicDrawView;

import java.util.Random;


public class SleepActivity extends AppCompatActivity {

    private CubicDrawView cubicview;
    private static final int MIN=2000;
    private static final int MAX=7000;
    private BreathwaveView breathView;
    private Random r;
    private Handler handler;
    private Rolling rolling;
    private Rolling rolling2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cubicview=(CubicDrawView) findViewById(R.id.cubicdrawview);
        breathView=(BreathwaveView) findViewById(R.id.breathview);

        handler= new Handler();
        rolling=new Rolling(30);
        rolling2=new Rolling(30);
        initBreathing();

    }



    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            while(true) {
                r = new Random();
                int i1 = r.nextInt(MAX - MIN) + MIN;
                passToActual(i1);
                try {
                    Thread.sleep(35);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private void initBreathing() {
        new Thread(runnable).start();
    }

    private void passToActual(final double i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rolling.add(i);
                rolling2.add(rolling.getaverage());
                breathView.addSample(SystemClock.elapsedRealtime(),rolling2.getaverage());
            }
        });
    }

    @Override
    protected void onStop() {
        if(handler!=null)
            handler.removeCallbacks(runnable);
        super.onStop();
    }

    public void clearData(View view) {
        cubicview.clear();
    }
}
