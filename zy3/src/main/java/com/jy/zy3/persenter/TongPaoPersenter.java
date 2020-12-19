package com.jy.zy3.persenter;

import com.jy.zy3.base.BasePresenter;
import com.jy.zy3.interfaces.CallBack;
import com.jy.zy3.interfaces.tongpao.ITongPao;
import com.jy.zy3.model.TongPaoModel;
import com.jy.zy3.model.data.tongpao.BannerBean;
import com.jy.zy3.model.data.tongpao.RecommendBean;
import com.jy.zy3.model.data.tongpao.TopicBean;
import com.jy.zy3.model.data.tongpao.UserBean;

public class TongPaoPersenter extends BasePresenter<ITongPao.View> implements ITongPao.Persenter {

    ITongPao.Model model;
    ITongPao.View view;

    public TongPaoPersenter(ITongPao.View view) {
        this.view = view;
        model = new TongPaoModel();
    }

    @Override
    public void getBanner() {
        model.getBanner(new CallBack() {
            @Override
            public void success(Object o) {
                if (view != null) {
                    view.getBanner((BannerBean) o);
                }
            }

            @Override
            public void onFaile(String mag) {
                if (view != null) {
                    view.tips(mag);
                }
            }
        });
    }

    @Override
    public void getRecommend() {
        model.getRecommend(new CallBack() {
            @Override
            public void success(Object o) {
                if (view != null) {
                    view.getRecommend((RecommendBean) o);
                }
            }

            @Override
            public void onFaile(String mag) {
                if (view != null) {
                    view.tips(mag);
                }
            }
        });
    }

    @Override
    public void getTopic() {
        model.getTopic(new CallBack() {
            @Override
            public void success(Object o) {
                if (view != null) {
                    view.getTopic((TopicBean) o);
                }
            }

            @Override
            public void onFaile(String mag) {
                if (view != null) {
                    view.tips(mag);
                }
            }
        });
    }

    @Override
    public void getUser() {
        model.getUser(new CallBack() {
            @Override
            public void success(Object o) {
                if (view != null) {
                    view.getUser((UserBean) o);
                }
            }

            @Override
            public void onFaile(String mag) {
                if (view != null) {
                    view.tips(mag);
                }
            }
        });
    }
}
