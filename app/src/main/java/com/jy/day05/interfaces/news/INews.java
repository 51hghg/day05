package com.jy.day05.interfaces.news;

import com.jy.day05.interfaces.CallBack;
import com.jy.day05.interfaces.IBasePresenter;
import com.jy.day05.interfaces.IBaseView;
import com.jy.day05.interfaces.IModel;

public interface INews {
    interface View extends IBaseView {
        void getNewsReturn(String result);
    }
    interface  Presenter extends IBasePresenter {
        void getNews();
    }
    interface Model extends IModel {
        void getNews(CallBack callBack);
    }


}
