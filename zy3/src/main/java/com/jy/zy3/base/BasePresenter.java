package com.jy.zy3.base;

import com.jy.zy3.interfaces.IBasePersenter;
import com.jy.zy3.interfaces.IBaseView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePersenter<V> {
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
