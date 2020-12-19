package com.jy.zy3.interfaces.tongpao;

import com.jy.zy3.interfaces.CallBack;
import com.jy.zy3.interfaces.IBaseModel;
import com.jy.zy3.interfaces.IBasePersenter;
import com.jy.zy3.interfaces.IBaseView;
import com.jy.zy3.model.data.tongpao.BannerBean;
import com.jy.zy3.model.data.tongpao.RecommendBean;
import com.jy.zy3.model.data.tongpao.TopicBean;
import com.jy.zy3.model.data.tongpao.UserBean;

public interface ITongPao {
    interface View extends IBaseView{
        void getBanner(BannerBean bannerBean);
        void getRecommend(RecommendBean recommendBean);
        void getTopic(TopicBean topicBean);
        void getUser(UserBean userBean);
    }

    interface Model extends IBaseModel{
        void getBanner(CallBack callBack);
        void getRecommend(CallBack callBack);
        void getTopic(CallBack callBack);
        void getUser(CallBack callBack);
    }

    interface Persenter extends IBasePersenter<View>{
        void getBanner();
        void getRecommend();
        void getTopic();
        void getUser();
    }
}
