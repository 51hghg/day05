package com.jy.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView mImgIv;
    private TextView mTitleTv;
    private Timer timer;
    int index = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImgIv = (ImageView) findViewById(R.id.iv_img);
        mTitleTv = (TextView) findViewById(R.id.tv_title);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set);
        mImgIv.setAnimation(animation);

        timer = new Timer();

        mTitleTv.setText("倒计时:"+index);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                index--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (index==0){
                            startActivity(new Intent(MainActivity.this,ViewpagerActivity.class));
                        }else{
                            mTitleTv.setText("倒计时:"+index);
                        }
                    }
                });
            }
        },1000,1000);
    }
}
