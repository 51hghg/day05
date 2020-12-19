package com.jy.day05.interfaces;

public interface CallBack<T> {
    void onFaile(String msg);
    void onSuccess(T t);
}
