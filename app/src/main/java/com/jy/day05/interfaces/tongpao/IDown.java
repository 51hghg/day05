package com.jy.day05.interfaces.tongpao;

import com.jy.day05.interfaces.CallBack;

public interface IDown {
    interface DownModel {
        void downImage(String url, CallBack callBack);
    }
}
