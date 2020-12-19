package com.jy.zy.interfaces;

public interface CallBack<T> {
    void success(T t);

    void onFaile(String msg);
}
