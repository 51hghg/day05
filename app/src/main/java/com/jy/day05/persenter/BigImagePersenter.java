package com.jy.day05.persenter;

import com.jy.day05.api.ImageApi;
import com.jy.day05.base.BasePresenter;
import com.jy.day05.interfaces.CallBack;
import com.jy.day05.interfaces.home.IHome;
import com.jy.day05.interfaces.tongpao.IBigImage;
import com.jy.day05.model.BigImageModel;

public class BigImagePersenter extends BasePresenter<IBigImage.View> implements IBigImage.Persenter {

    BigImageModel mode;
    IBigImage.View view;

    public BigImagePersenter(IBigImage.View view) {
        this.view = view;
        mode = new BigImageModel();
    }

    @Override
    public void downImg(String url) {
        mode.downImage(url, new CallBack() {
            @Override
            public void onFaile(String msg) {

            }

            @Override
            public void onSuccess(Object o) {
                if (view != null) {
                    view.downReturn((String) o);
                }
            }
        });
    }
}
