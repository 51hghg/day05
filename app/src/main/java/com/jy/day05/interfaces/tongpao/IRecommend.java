package com.jy.day05.interfaces.tongpao;

import com.jy.day05.interfaces.CallBack;
import com.jy.day05.interfaces.IBasePresenter;
import com.jy.day05.interfaces.IBaseView;
import com.jy.day05.interfaces.IModel;
import com.jy.day05.model.data.tongpao.BannerBean;
import com.jy.day05.model.data.tongpao.HotBean;
import com.jy.day05.model.data.tongpao.PersonBean;
import com.jy.day05.model.data.tongpao.RecommendBean;
import com.jy.day05.model.data.tongpao.TopicBean;
import com.jy.day05.model.data.tongpao.UserBean;

public interface IRecommend {
    interface View extends IBaseView {
        //定义一个被推荐页实现的View层接口方法
        void getRecommendReturn(RecommendBean result);

        void getBannerReturn(BannerBean bannerBean);

        void getTopisReturn(TopicBean topicBean);

        void getUserReturn(UserBean userBean);

        void persenter(PersonBean personBean);

        void getHot(HotBean hotBean);
    }

    interface Persenter extends IBasePresenter<View> {
        //定义一个首页推荐页面V层调用的接口
        void getRecommend();

        void getBanner();

        void getTopic();

        void getUser();

        void getPerson();

        void gethot();
    }

    interface Model extends IModel {
        //定义一个加载推荐数据的接口方法 被P层
        void loadRecommend(CallBack callBack);

        void loadBanner(CallBack callBack);

        void loadTopic(CallBack callBack);

        void loadUser(CallBack callBack);

        void loadPerson(CallBack callBack);

        void getHot(CallBack callBack);
    }

}
