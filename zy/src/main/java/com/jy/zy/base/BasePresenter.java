package com.jy.zy.base;



import com.jy.zy.interfaces.IBasePersenter;
import com.jy.zy.interfaces.IBaseView;

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
