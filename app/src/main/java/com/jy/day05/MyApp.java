package com.jy.day05;

import android.app.Application;

import androidx.annotation.NonNull;

public class MyApp extends Application {
    private static MyApp app;

    public static MyApp getApp(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
