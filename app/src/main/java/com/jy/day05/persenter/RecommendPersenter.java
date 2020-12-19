package com.jy.day05.persenter;

import com.jy.day05.base.BasePresenter;
import com.jy.day05.interfaces.CallBack;
import com.jy.day05.interfaces.tongpao.IRecommend;
import com.jy.day05.model.RecommendModel;
import com.jy.day05.model.data.tongpao.BannerBean;
import com.jy.day05.model.data.tongpao.HotBean;
import com.jy.day05.model.data.tongpao.PersonBean;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.model.data.tongpao.UserBean;
import com.jy.day05.ui.fragment.Recommendragment;

public class RecommendPersenter extends BasePresenter<IRecommend.View> implements IRecommend.Persenter {

    IRecommend.View view;
    IRecommend.Model model;

    public RecommendPersenter(IRecommend.View view) {
        this.view = view;
        this.model = new RecommendModel();
    }


    @Override
    public void getRecommend() {
        model.loadRecommend(new CallBack() {
            @Override
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }

            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getRecommendReturn((RecommendBean) o);
                }
            }
        });
    }

    @Override
    public void getBanner() {
        model.loadBanner(new CallBack() {
            @Override
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }

            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getBannerReturn((BannerBean) o);
                }
            }
        });
    }

    @Override
    public void getTopic() {
        model.loadTopic(new CallBack() {
            @Override
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }

            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getTopisReturn((TopicBean) o);
                }
            }
        });
    }

    @Override
    public void getUser() {
        model.loadUser(new CallBack() {
            @Override
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }

            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getUserReturn((UserBean) o);
                }
            }
        });
    }

    @Override
    public void getPerson() {
        model.loadPerson(new CallBack() {
            @Override
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }

            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.persenter((PersonBean) o);
                }
            }
        });
    }

    @Override
    public void gethot() {
        model.getHot(new CallBack() {
            @Override
            public void onFaile(String msg) {
                if (view != null) {
                    view.tips(msg);
                }
            }

            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.getHot((HotBean) o);
                }
            }
        });
    }
}
