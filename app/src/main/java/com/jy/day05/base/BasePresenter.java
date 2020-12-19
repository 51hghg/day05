package com.jy.day05.base;

import com.jy.day05.interfaces.IBasePresenter;
import com.jy.day05.interfaces.IBaseView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;
    WeakReference<V> weakReference;

    @Override
    public void attachView(V view) {
        weakReference=new WeakReference<V>(view);
        mView=weakReference.get();
    }

    @Override
    public void unArrachView() {
        mView=null;
    }
}
