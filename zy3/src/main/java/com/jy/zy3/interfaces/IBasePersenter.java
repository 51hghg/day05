package com.jy.zy3.interfaces;

public interface IBasePersenter<V extends IBaseView> {
    void attachView(V view);

    void unArrachView();
}
