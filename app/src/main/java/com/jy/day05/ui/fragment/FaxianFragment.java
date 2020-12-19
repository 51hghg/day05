package com.jy.day05.ui.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.day05.R;
import com.jy.day05.base.BaseFragment;
import com.jy.day05.interfaces.tongpao.IRecommend;
import com.jy.day05.model.data.tongpao.BannerBean;
import com.jy.day05.model.data.tongpao.HotBean;
import com.jy.day05.model.data.tongpao.PersonBean;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.model.data.tongpao.UserBean;
import com.jy.day05.persenter.RecommendPersenter;
import com.jy.day05.ui.adapter.HotAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FaxianFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {

    @BindView(R.id.recycler1)
    RecyclerView recycler1;
    @BindView(R.id.recycler2)
    RecyclerView recycler2;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private ArrayList<HotBean.DataBean> list;
    private HotAdapter hotAdapter;

    @Override
    public int getLatout() {
        return R.layout.fragment_faxian;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        recycler2.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        hotAdapter = new HotAdapter(list, getActivity());
        recycler2.setAdapter(hotAdapter);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TuijianFragment());
        fragments.add(new TuijianFragment());
        fragments.add(new TuijianFragment());
        fragments.add(new TuijianFragment());

        viewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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

        tablayout.setupWithViewPager(viewpager);

        tablayout.getTabAt(0).setText("热点");
        tablayout.getTabAt(1).setText("妆造");
        tablayout.getTabAt(2).setText("图赏");
        tablayout.getTabAt(3).setText("百科");
    }

    @Override
    public RecommendPersenter createPresenter() {
        return new RecommendPersenter(this);
    }

    @Override
    public void initData() {
        presenter.gethot();
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

    }

    @Override
    public void getHot(HotBean hotBean) {
        List<HotBean.DataBean> data = hotBean.getData();
        list.addAll(data);
        hotAdapter.notifyDataSetChanged();
    }
}