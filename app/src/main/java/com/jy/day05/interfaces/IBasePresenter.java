package com.jy.day05.interfaces;

public interface IBasePresenter<V extends IBaseView> {
    void  attachView(V view);
    void unArrachView();
}
