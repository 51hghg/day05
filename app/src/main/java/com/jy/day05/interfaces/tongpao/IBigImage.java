package com.jy.day05.interfaces.tongpao;

import com.jy.day05.interfaces.IBasePresenter;
import com.jy.day05.interfaces.IBaseView;
import com.jy.day05.interfaces.IModel;

public interface IBigImage {
    interface View extends IBaseView {
        void downReturn(String path);
    }

    interface Persenter extends IBasePresenter<View> {
        void downImg(String url);
    }

    interface Model extends IModel {
        void getBigImage();
    }
}
