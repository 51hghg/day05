package com.jy.day05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jy.day05.base.BaseActivity;
import com.jy.day05.interfaces.tongpao.IRecommend;
import com.jy.day05.model.data.tongpao.BannerBean;
import com.jy.day05.model.data.tongpao.HotBean;
import com.jy.day05.model.data.tongpao.PersonBean;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.model.data.tongpao.UserBean;
import com.jy.day05.persenter.RecommendPersenter;
import com.jy.day05.ui.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends BaseActivity<RecommendPersenter> implements IRecommend.View {
    @BindView(R.id.img_cancel)
    ImageView imgCancel;
    @BindView(R.id.img_option)
    ImageView imgOption;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tet_name)
    TextView tetName;
    @BindView(R.id.btn_love)
    Button btnLove;
    @BindView(R.id.btn_liao)
    Button btnLiao;
    @BindView(R.id.rt)
    RelativeLayout rt;
    @BindView(R.id.tv_qianming)
    TextView tvQianming;
    @BindView(R.id.txt_collect)
    TextView txtCollect;
    @BindView(R.id.txt_artice)
    TextView txtArtice;
    @BindView(R.id.txt_exp)
    TextView txtExp;
    @BindView(R.id.rt2)
    RelativeLayout rt2;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.con2)
    ConstraintLayout con2;
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.tabLayout_header)
    TabLayout tabLayoutHeader;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PersonFragment());
        fragments.add(new PersonFragment());
        fragments.add(new PersonFragment());
        fragments.add(new PersonFragment());
        fragments.add(new PersonFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabLayoutHeader.setupWithViewPager(viewPager);
        tabLayoutHeader.getTabAt(0).setText("你猜");
        tabLayoutHeader.getTabAt(1).setText("你猜");
        tabLayoutHeader.getTabAt(2).setText("你猜");
        tabLayoutHeader.getTabAt(3).setText("你猜");
        tabLayoutHeader.getTabAt(4).setText("你猜");
    }

    @Override
    protected RecommendPersenter createPersenter() {
        return new RecommendPersenter(this);
    }

    @Override
    protected void initData() {
        presenter.getPerson();
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {

    }

    @Override
    public void getBannerReturn(BannerBean bannerBean) {

    }

    @Override
    public void getTopisReturn(TopicBean topicBean) {

    }

    @Override
    public void getUserReturn(UserBean userBean) {

    }

    @Override
    public void persenter(PersonBean personBean) {
        PersonBean.DataBean data = personBean.getData();
        Glide.with(this).load(data.getHeadUrl()).into(imgBack);
        Glide.with(this).load(data.getHeadUrl()).apply(RequestOptions.circleCropTransform()).into(imgHeader);
        tvQianming.setText(data.getSignature());
        txtCollect.setText(data.getCollectnumber() + "");
        txtArtice.setText(data.getArticelnumber() + "");
        txtExp.setText(data.getExpScore() + "");
        if(data.getSex().equals("1")){
            imgSex.setImageResource(R.mipmap.boy);
        }else{
            imgSex.setImageResource(R.mipmap.gril);
        }

        tetName.setText(data.getNickName());
    }

    @Override
    public void getHot(HotBean hotBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
