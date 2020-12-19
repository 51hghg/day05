package com.jy.zy.persenter;

import com.jy.zy.base.BasePresenter;
import com.jy.zy.interfaces.CallBack;
import com.jy.zy.interfaces.tongpao.IRecommend;
import com.jy.zy.model.RecommendModel;
import com.jy.zy.model.data.tongpao.BannerBean;
import com.jy.zy.model.data.tongpao.RecommendBean;
import com.jy.zy.model.data.tongpao.TopicBean;
import com.jy.zy.model.data.tongpao.UserBean;

public class RecommendPersenter extends BasePresenter<IRecommend.View> implements IRecommend.Persenter {

    IRecommend.Model model;
    IRecommend.View view;

    public RecommendPersenter(IRecommend.View view) {
        this.view = view;
        model = new RecommendModel();
    }

    @Override
    public void getRecommend() {
        model.getRecommend(new CallBack() {
            @Override
            public void success(Object o) {
                if (view!=null){
                    view.getRecommend((RecommendBean) o);
                }
            }

            @Override
            public void onFaile(String msg) {
                if (view!=null){
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getBanner() {
        model.getBanner(new CallBack() {
            @Override
            public void success(Object o) {
                if (view!=null){
                    view.getBanner((BannerBean) o);
                }
            }

            @Override
            public void onFaile(String msg) {
                if (view!=null){
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
                if (view!=null){
                    view.getTopic((TopicBean) o);
                }
            }

            @Override
            public void onFaile(String msg) {
                if (view!=null){
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
                if (view!=null){
                    view.getUser((UserBean) o);
                }
            }

            @Override
            public void onFaile(String msg) {
                if (view!=null){
                    view.tips(msg);
                }
            }
        });
    }
}
