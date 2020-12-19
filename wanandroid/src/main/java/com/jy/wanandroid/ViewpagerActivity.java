package com.jy.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewpagerActivity extends AppCompatActivity {

    private ViewPager mImgVp;
    int [] arr = {R.drawable.b,R.drawable.c,R.drawable.d};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
    }

    private void initView() {
        mImgVp = (ViewPager) findViewById(R.id.vp_img);

        mImgVp.setAdapter(new PagerAdapter() {
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View)object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View root = LayoutInflater.from(ViewpagerActivity.this).inflate(R.layout.item_paget, null);

                ImageView mPager = root.findViewById(R.id.iv_pager);

                Button mOk = root.findViewById(R.id.btn_ok);

                mPager.setBackgroundResource(arr[position]);

                if (position==arr.length-1){
                    mOk.setVisibility(View.VISIBLE);

                    mOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(ViewpagerActivity.this,RecyclerActivity.class));
                        }
                    });
                }

                container.addView(root);

                return root;
            }

            @Override
            public int getCount() {
                return arr.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }
        });
    }
}
