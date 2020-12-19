package com.jy.day05.api;

import com.jy.day05.model.data.CityData;
import com.jy.day05.model.data.WeatherData;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ServiceApi {
    String BASE_URL="https://jisutqybmf.market.alicloudapi.com/weather/";

    @GET("city")
    Flowable<CityData> getCity();

    @GET("query")
    Flowable<WeatherData> queryWeather(Map<String, String> map);

}
