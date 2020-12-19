package com.jy.zy3.api;

import com.jy.zy3.model.data.tongpao.BannerBean;
import com.jy.zy3.model.data.tongpao.RecommendBean;
import com.jy.zy3.model.data.tongpao.TopicBean;
import com.jy.zy3.model.data.tongpao.UserBean;

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
