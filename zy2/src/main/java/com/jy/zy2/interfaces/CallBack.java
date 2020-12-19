package com.jy.zy2.interfaces;

public interface CallBack<T> {
    void success(T t);

    void onFaile(String msg);
}
