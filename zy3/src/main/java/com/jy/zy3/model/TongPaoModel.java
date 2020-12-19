package com.jy.zy3.model;

import com.jy.zy3.base.BaseModel;
import com.jy.zy3.interfaces.CallBack;
import com.jy.zy3.interfaces.tongpao.ITongPao;
import com.jy.zy3.model.data.tongpao.BannerBean;
import com.jy.zy3.model.data.tongpao.RecommendBean;
import com.jy.zy3.model.data.tongpao.TopicBean;
import com.jy.zy3.model.data.tongpao.UserBean;
import com.jy.zy3.net.CommonSubscriber;
import com.jy.zy3.net.HttpManager;
import com.jy.zy3.utils.RxUtils;

public class TongPaoModel extends BaseModel implements ITongPao.Model {
    @Override
    public void getBanner(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi()
                        .getBanner()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BannerBean>(callBack) {
                            @Override
                            public void onNext(BannerBean bannerBean) {
                                callBack.success(bannerBean);
                            }
                        })
        );
    }

    @Override
    public void getRecommend(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi()
                        .getRecommend()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<RecommendBean>(callBack) {
                            @Override
                            public void onNext(RecommendBean recommendBean) {
                                callBack.success(recommendBean);
                            }
                        })
        );
    }

    @Override
    public void getTopic(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi()
                        .getTopic()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<TopicBean>(callBack) {
                            @Override
                            public void onNext(TopicBean topicBean) {
                                callBack.success(topicBean);
                            }
                        })
        );
    }

    @Override
    public void getUser(CallBack callBack) {
        addDisposable(
                HttpManager.getHttpManager().getTongpaoApi()
                        .getUser()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<UserBean>(callBack) {
                            @Override
                            public void onNext(UserBean userBean) {
                                callBack.success(userBean);
                            }
                        })
        );
    }
}
