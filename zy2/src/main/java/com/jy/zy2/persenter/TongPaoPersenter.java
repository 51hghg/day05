package com.jy.zy2.persenter;

import com.jy.zy2.base.BasePresenter;
import com.jy.zy2.interfaces.CallBack;
import com.jy.zy2.interfaces.IBaseView;
import com.jy.zy2.interfaces.tongpao.ITongPao;
import com.jy.zy2.model.RecommendModel;
import com.jy.zy2.model.data.tongpao.BannerBean;
import com.jy.zy2.model.data.tongpao.RecommendBean;
import com.jy.zy2.model.data.tongpao.TopicBean;
import com.jy.zy2.model.data.tongpao.UserBean;

public class TongPaoPersenter extends BasePresenter<ITongPao.View> implements ITongPao.Persenter {

    ITongPao.Model model;
    ITongPao.View view;

    public TongPaoPersenter(ITongPao.View view) {
        this.view = view;
        model = new RecommendModel();
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
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
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
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
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
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
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
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }
        });
    }
}
