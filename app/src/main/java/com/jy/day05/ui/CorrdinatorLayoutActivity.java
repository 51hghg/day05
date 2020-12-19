package com.jy.day05.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.day05.R;
import com.jy.day05.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CorrdinatorLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrdinator_layout);
        initView();
    }

    private void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        for(int i=0;i<100; i++){
            list.add("item"+i);
        }
        myAdapter = new MyAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

    }

    class MyAdapter extends BaseAdapter {
        public MyAdapter(Context context, List<String> data){
            super(data, context);
        }

        @Override
        protected int getLayout() {
            return R.layout.layout_coordinator_item;
        }

        @Override
        protected void bindData(Object data, VH vh) {
            TextView txtInfo = (TextView)(vh.getViewByid(R.id.txt_info));
            //加载数据
            txtInfo.setText(String.valueOf(data));
        }
    }
}
