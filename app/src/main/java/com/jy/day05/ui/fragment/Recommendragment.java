package com.jy.day05.ui.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.jy.day05.ui.adapter.DiscussedAdapter;
import com.jy.day05.ui.adapter.RecommondAdapter;
import com.jy.day05.ui.adapter.UserAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Recommendragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {
    @BindView(R.id.banner_main)
    Banner bannerMain;
    @BindView(R.id.tv_hot_topic)
    TextView tvHotTopic;
    @BindView(R.id.tv_more_topic)
    TextView tvMoreTopic;
    @BindView(R.id.recyclerview_talk)
    RecyclerView recyclerviewTalk;
    @BindView(R.id.recyclerview_recommend)
    RecyclerView recyclerviewRecommend;
    @BindView(R.id.recyclerview_hotuser)
    RecyclerView recyclerviewHotuser;
    private ArrayList<RecommendBean.DataBean.PostDetailBean> postDetailBeans;
    private ArrayList<UserBean.DataBean> userBeans;
    private ArrayList<TopicBean.DataBean> topicBeans;
    private RecommondAdapter recomendAdapter;
    private DiscussedAdapter disCussedAdapter;
    private UserAdapter userAdapter;

    @Override
    public int getLatout() {
        return R.layout.fragment_recommendragment;
    }

    @Override
    public void initView() {
        postDetailBeans = new ArrayList<>();
        userBeans = new ArrayList<>();
        topicBeans = new ArrayList<>();

        recyclerviewTalk.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        disCussedAdapter = new DiscussedAdapter(topicBeans, getActivity());
        recyclerviewTalk.setAdapter(disCussedAdapter);

        recyclerviewRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        recomendAdapter = new RecommondAdapter(postDetailBeans, getActivity());
        recyclerviewRecommend.setAdapter(recomendAdapter);

        recyclerviewHotuser.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        userAdapter = new UserAdapter(userBeans, getActivity());
        recyclerviewHotuser.setAdapter(userAdapter);


    }

    @Override
    public RecommendPersenter createPresenter() {
        return new RecommendPersenter(this);
    }

    @Override
    public void initData() {
        presenter.getBanner();
        presenter.getRecommend();
        presenter.getTopic();
        presenter.getUser();
    }

    @Override
    public void getRecommendReturn(RecommendBean result) {
        RecommendBean.DataBean.PostDetailBean postDetail = result.getData().getPostDetail();
        postDetailBeans.add(postDetail);
        recomendAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBannerReturn(BannerBean bannerBean) {
        List<BannerBean.DataBean.ListBean> list = bannerBean.getData().getList();
        bannerMain.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerBean.DataBean.ListBean listBean = (BannerBean.DataBean.ListBean) path;
                Glide.with(context).load(listBean.getBanner()).into(imageView);
            }
        }).start();
    }

    @Override
    public void getTopisReturn(TopicBean topicBean) {
        List<TopicBean.DataBean> data = topicBean.getData();
        topicBeans.addAll(data);
        disCussedAdapter.notifyDataSetChanged();
    }

    @Override
    public void getUserReturn(UserBean userBean) {
        List<UserBean.DataBean> data = userBean.getData();
        userBeans.addAll(data);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void persenter(PersonBean personBean) {

    }

    @Override
    public void getHot(HotBean hotBean) {

    }
}
