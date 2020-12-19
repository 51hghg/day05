package com.jy.zy.api;

import com.jy.zy.model.data.tongpao.BannerBean;
import com.jy.zy.model.data.tongpao.RecommendBean;
import com.jy.zy.model.data.tongpao.TopicBean;
import com.jy.zy.model.data.tongpao.UserBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface TongPaoApi {
    String BASE_URL="http://cdwan.cn:7000/tongpao/";

    @GET("home/recommend.json")
    Flowable<RecommendBean> getRecommend();

    @GET("home/banner.json")
    Flowable<BannerBean> getBanner();

    @GET("home/topic_discussed.json")
    Flowable<TopicBean> getTopic();

    @GET("http://cdwan.cn:7000/tongpao/home/hot_user.json")
    Flowable<UserBean> getUser();
}
