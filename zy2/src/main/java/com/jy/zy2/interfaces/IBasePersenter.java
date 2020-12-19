package com.jy.zy2.interfaces;

import android.widget.ImageView;

public interface IBasePersenter<V extends IBaseView> {
    void attachView(V view);

    void unArrachView();
}
