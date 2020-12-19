package com.jy.day05.api;

import com.jy.day05.model.data.tongpao.BannerBean;
import com.jy.day05.model.data.tongpao.HotBean;
import com.jy.day05.model.data.tongpao.PersonBean;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.model.data.tongpao.UserBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface TongpaoApi {
    String BASE_URL = "http://cdwan.cn:7000/tongpao/";

    @GET("home/recommend.json")
    Flowable<RecommendBean> getRecommend();

    @GET("home/banner.json")
    Flowable<BannerBean> getBanner();

    @GET("home/topic_discussed.json")
    Flowable<TopicBean> getTopic();

    @GET("http://cdwan.cn:7000/tongpao/home/hot_user.json")
    Flowable<UserBean> getUser();

    @GET("home/personal.json")
    Flowable<PersonBean> getPerson();

    @GET("discover/hot_activity.json")
    Flowable<HotBean> getHot();
}

