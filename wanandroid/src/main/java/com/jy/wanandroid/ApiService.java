package com.jy.wanandroid;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String baseUrl = "http://www.qubaobei.com/";

    @GET("ios/cf/dish_list.php")
    Call<FoodBean> get(@Query("stage_id")String stage_id,@Query("limit")String limit,@Query("page")int page);

    @GET("ios/cf/dish_list.php")
    Observable<FoodBean> getDataRx(@Query("stage_id")String stage_id, @Query("limit")String limit, @Query("page")int page);
}
