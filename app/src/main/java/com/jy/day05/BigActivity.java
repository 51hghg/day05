package com.jy.day05;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.jy.day05.base.BaseActivity;
import com.jy.day05.base.BasePresenter;
import com.jy.day05.interfaces.tongpao.IBigImage;
import com.jy.day05.persenter.BigImagePersenter;
import com.jy.day05.utils.ImageLoader;
import com.jy.day05.utils.TxtUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BigActivity extends BaseActivity<BigImagePersenter> implements IBigImage.View {

    @BindView(R.id.txt_return)
    TextView txtReturn;
    @BindView(R.id.txt_page)
    TextView txtPage;
    @BindView(R.id.txt_down)
    TextView txtDown;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private int currentPos;
    private List<String> urls;

    @Override
    protected int getLayout() {
        return R.layout.activity_big;
    }

    @Override
    protected void initView() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
//                txtPage.setText(currentPos + 1 + "/" + urls.size());
                undatePage();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected BigImagePersenter createPersenter() {
        return new BigImagePersenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("data")) {
            Bundle data = intent.getBundleExtra("data");
            if (data != null) {
                urls = data.getStringArrayList("urls");
                currentPos = data.getInt("postion");
                undatePage();
//                txtPage.setText(currentPos + 1 + "/" + urls.size());
            }
        }

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return urls.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view= LayoutInflater.from(BigActivity.this).inflate(R.layout.big_item_img,null);
                final ImageView mImg = view.findViewById(R.id.iv_img);
                Glide.with(BigActivity.this).load(urls.get(position)).into(mImg);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        viewPager.setCurrentItem(currentPos);
    }

    @OnClick({R.id.txt_return, R.id.txt_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_return:
                finish();
                break;
            case R.id.txt_down:
                downImg();
                break;
        }
    }

    private void undatePage() {

        if (currentPos < urls.size()) {
            String page = String.valueOf(currentPos+1 +"/"+ urls.size());
            TxtUtils.setTextView(txtPage, page);
            //判断是否有下载过
            String imgUrl = urls.get(currentPos);
            String[] arr = ImageLoader.splitUrl(imgUrl);
            String imgName = arr[1];
            String path = arr[2];
            File file = new File(path);
            if (file.exists()) {
                txtDown.setVisibility(View.GONE);
            } else {
                txtDown.setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(this, "当前的图片位置越界", Toast.LENGTH_SHORT).show();
        }
    }

    private void downImg() {
        String imgUrl = urls.get(currentPos);
        presenter.downImg(imgUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void downReturn(String path) {
        Log.i("TAG", path);
    }
}
