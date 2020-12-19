package com.jy.zy.interfaces.tongpao;

import com.jy.zy.interfaces.CallBack;
import com.jy.zy.interfaces.IBasePersenter;
import com.jy.zy.interfaces.IBaseView;
import com.jy.zy.interfaces.IModel;
import com.jy.zy.model.data.tongpao.BannerBean;
import com.jy.zy.model.data.tongpao.RecommendBean;
import com.jy.zy.model.data.tongpao.TopicBean;
import com.jy.zy.model.data.tongpao.UserBean;

public interface IRecommend {
    interface View extends IBaseView{
        void getRecommend(RecommendBean recommendBean);
        void getBanner(BannerBean bannerBean);
        void getTopic(TopicBean topicBean);
        void getUser(UserBean userBean);
    }

    interface Model extends IModel{
        void getRecommend(CallBack callBack);
        void getBanner(CallBack callBack);
        void getTopic(CallBack callBack);
        void getUser(CallBack callBack);
    }

    interface Persenter extends IBasePersenter<View> {
        void getRecommend();
        void getBanner();
        void getTopic();
        void getUser();
    }
}
