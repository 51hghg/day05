package com.jy.zy3.interfaces;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {
    void addDisposable(Disposable disposable);

    void clear();
}
