package com.jy.zy.ui.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.zy.R;
import com.jy.zy.base.BaseFragment;
import com.jy.zy.interfaces.tongpao.IRecommend;
import com.jy.zy.model.data.tongpao.BannerBean;
import com.jy.zy.model.data.tongpao.RecommendBean;
import com.jy.zy.model.data.tongpao.TopicBean;
import com.jy.zy.model.data.tongpao.UserBean;
import com.jy.zy.persenter.RecommendPersenter;
import com.jy.zy.ui.adapter.RecommendAdapter;
import com.jy.zy.ui.adapter.TocipAdapter;
import com.jy.zy.ui.adapter.UserAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {

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
    private ArrayList<RecommendBean.DataBean.PostDetailBean> recommend;
    private ArrayList<TopicBean.DataBean> topic;
    private ArrayList<UserBean.DataBean> user;
    private TocipAdapter topicAdapter;
    private RecommendAdapter recommendAdapter;
    private UserAdapter userAdapter;

    @Override
    public int getLatout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initView() {
        recommend = new ArrayList<>();
        topic = new ArrayList<>();
        user = new ArrayList<>();

        recyclerviewTalk.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        topicAdapter = new TocipAdapter(topic, getActivity());
        recyclerviewTalk.setAdapter(topicAdapter);

        recyclerviewRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        recommendAdapter = new RecommendAdapter(recommend, getActivity());
        recyclerviewRecommend.setAdapter(recommendAdapter);

        recyclerviewHotuser.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        userAdapter = new UserAdapter(user, getActivity());
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
    public void getRecommend(RecommendBean recommendBean) {
        RecommendBean.DataBean.PostDetailBean bean = recommendBean.getData().getPostDetail();
        recommend.add(bean);
        recommendAdapter.notifyDataSetChanged();
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
    public void getTopic(TopicBean topicBean) {
        List<TopicBean.DataBean> data = topicBean.getData();
        topic.addAll(data);
        topicAdapter.notifyDataSetChanged();
    }

    @Override
    public void getUser(UserBean userBean) {
        List<UserBean.DataBean> data = userBean.getData();
        user.addAll(data);
        userAdapter.notifyDataSetChanged();
    }
}
