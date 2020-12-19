package com.jy.day05.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ImageApi {
    @GET
    Flowable<ResponseBody> downImage(@Url String url);
}
