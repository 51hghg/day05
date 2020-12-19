package com.jy.zy3.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.zy3.R;
import com.jy.zy3.base.BaseFragment;
import com.jy.zy3.interfaces.tongpao.ITongPao;
import com.jy.zy3.model.data.tongpao.BannerBean;
import com.jy.zy3.model.data.tongpao.RecommendBean;
import com.jy.zy3.model.data.tongpao.TopicBean;
import com.jy.zy3.model.data.tongpao.UserBean;
import com.jy.zy3.persenter.TongPaoPersenter;
import com.jy.zy3.ui.adapter.RecommendAdapter;
import com.jy.zy3.ui.adapter.TopicAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TongPaoFragment extends BaseFragment<TongPaoPersenter> implements ITongPao.View {

    @BindView(R.id.banner)
    Banner banner;
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
    private ArrayList<RecommendBean.DataBean.PostDetailBean> recommendList;
    private ArrayList<TopicBean.DataBean> topicList;
    private ArrayList<UserBean.DataBean> userList;
    private TopicAdapter topicAdapter;
    private RecommendAdapter recommendAdapter;

    @Override
    public int getLatout() {
        return R.layout.fragment_tong_pao;
    }

    @Override
    public void initView() {
        recommendList = new ArrayList<>();
        topicList = new ArrayList<>();
        userList = new ArrayList<>();

        recyclerviewTalk.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        topicAdapter = new TopicAdapter(topicList, getActivity());
        recyclerviewTalk.setAdapter(topicAdapter);

        recyclerviewRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        recommendAdapter = new RecommendAdapter(recommendList, getActivity());
        recyclerviewRecommend.setAdapter(recommendAdapter);
    }

    @Override
    public TongPaoPersenter createPresenter() {
        return new TongPaoPersenter(this);
    }


    @Override
    public void initData() {
        presenter.getBanner();
        presenter.getRecommend();
        presenter.getTopic();
        presenter.getUser();
    }

    @Override
    public void getBanner(BannerBean bannerBean) {
        List<BannerBean.DataBean.ListBean> list = bannerBean.getData().getList();
        banner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerBean.DataBean.ListBean listBean = (BannerBean.DataBean.ListBean) path;
                Glide.with(context).load(listBean.getBanner()).into(imageView);
            }
        }).start();
    }

    @Override
    public void getRecommend(RecommendBean recommendBean) {
        RecommendBean.DataBean.PostDetailBean postDetail = recommendBean.getData().getPostDetail();
        recommendList.add(postDetail);
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void getTopic(TopicBean topicBean) {
        List<TopicBean.DataBean> data = topicBean.getData();
        topicList.addAll(data);
        topicAdapter.notifyDataSetChanged();
    }

    @Override
    public void getUser(UserBean userBean) {

    }
}
