package com.jy.day05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.jy.day05.ui.CorrdinatorLayoutActivity;
import com.jy.day05.ui.HomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_share;
    private Button btn_coor;
    private Button btn_loac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_share = (Button) findViewById(R.id.btn_share);
        btn_coor = (Button) findViewById(R.id.btn_coor);

        btn_share.setOnClickListener(this);
        btn_coor.setOnClickListener(this);
        btn_loac = (Button) findViewById(R.id.btn_loac);
        btn_loac.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_coor:
                Intent intent1 = new Intent(MainActivity.this, CorrdinatorLayoutActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_loac:
                Intent intent2 = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent2);
                break;

        }
    }
}
