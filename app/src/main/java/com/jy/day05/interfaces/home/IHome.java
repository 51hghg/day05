package com.jy.day05.interfaces.home;

import com.jy.day05.interfaces.CallBack;
import com.jy.day05.interfaces.IBasePresenter;
import com.jy.day05.interfaces.IBaseView;
import com.jy.day05.interfaces.IModel;
import com.jy.day05.model.data.CityData;
import com.jy.day05.model.data.WeatherData;

import java.util.Map;

public interface IHome {

    //home业务下的 v层接口
    interface View extends IBaseView {
        //获取城市数据返回
        void getCityReturn(CityData result);
        //获取天气数据返回
        void getWeatherReturn(WeatherData result);
    }

    //home业务下 P层接口
    interface Persenter extends IBasePresenter<View> {
        void getCity();
        //获取天气数据--> v层的列表选择
        void getWeather(Map<String, String> map);
    }

    //home业务下的model
    interface Model extends IModel {
        void getCity(CallBack callback);

        //获取天气数据 --> p层调用m层的接口
        void getWeatcher(Map<String, String> map, CallBack callback);
    }

}
