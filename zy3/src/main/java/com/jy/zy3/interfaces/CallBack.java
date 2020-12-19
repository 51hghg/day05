package com.jy.zy3.interfaces;

public interface CallBack<T> {
    void success(T t);

    void onFaile(String mag);
}
