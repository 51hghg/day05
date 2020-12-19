package com.jy.day05.model;

import com.jy.day05.base.BaseModel;
import com.jy.day05.interfaces.CallBack;
import com.jy.day05.interfaces.tongpao.IRecommend;
import com.jy.day05.model.data.tongpao.BannerBean;
import com.jy.day05.model.data.tongpao.HotBean;
import com.jy.day05.model.data.tongpao.PersonBean;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.model.data.tongpao.UserBean;
import com.jy.day05.net.CommonSubscriber;
import com.jy.day05.net.HttpManager;
import com.jy.day05.utils.RxUtils;

public class RecommendModel extends BaseModel implements IRecommend.Model {

    @Override
    public void loadRecommend(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi().getRecommend()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<RecommendBean>(callBack) {
                            @Override
                            public void onNext(RecommendBean recommendBean) {
                                callBack.onSuccess(recommendBean);
                            }
                        })
        );
    }

    @Override
    public void loadBanner(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi().getBanner()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BannerBean>(callBack) {
                            @Override
                            public void onNext(BannerBean bannerBean) {
                                callBack.onSuccess(bannerBean);
                            }
                        })
        );
    }

    @Override
    public void loadTopic(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi().getTopic()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<TopicBean>(callBack) {
                            @Override
                            public void onNext(TopicBean topicBean) {
                                callBack.onSuccess(topicBean);
                            }
                        })
        );
    }

    @Override
    public void loadUser(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi().getUser()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<UserBean>(callBack) {
                            @Override
                            public void onNext(UserBean userBean) {
                                callBack.onSuccess(userBean);
                            }
                        })
        );
    }

    @Override
    public void loadPerson(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi().getPerson()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<PersonBean>(callBack) {
                            @Override
                            public void onNext(PersonBean personBean) {
                                callBack.onSuccess(personBean);
                            }
                        })
        );
    }

    @Override
    public void getHot(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi().getHot()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<HotBean>(callBack) {
                            @Override
                            public void onNext(HotBean hotBean) {
                                callBack.onSuccess(hotBean  );
                            }
                        })
        );
    }
}


