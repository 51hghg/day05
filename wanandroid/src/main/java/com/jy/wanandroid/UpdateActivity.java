package com.jy.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvUpdate;
    private EditText mEtTitle;
    private EditText mEtDes;
    /**
     * 修改
     */
    private Button mBtnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
    }

    private void initView() {
        mIvUpdate = (ImageView) findViewById(R.id.iv_update);
        mEtTitle = (EditText) findViewById(R.id.et_title);
        mEtDes = (EditText) findViewById(R.id.et_des);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnUpdate.setOnClickListener(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String des = intent.getStringExtra("des");
        String pic = intent.getStringExtra("pic");
        Glide.with(this).load(pic).into(mIvUpdate);
        mEtTitle.setText(title);
        mEtDes.setText(des);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_update:
                String title = mEtTitle.getText().toString();
                String des = mEtDes.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("title",title);
                intent.putExtra("des",des);
                setResult(2,intent);
                finish();
                break;
        }
    }
}
