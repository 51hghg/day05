package com.jy.zy3.net;

import com.jy.zy3.api.TongPaoApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 * 整个项目全局使用
 */
public class HttpManager {
    private static HttpManager httpManager;

    public static HttpManager getHttpManager() {
        if(httpManager==null){
            synchronized (HttpManager.class){
                if(httpManager==null){
                    httpManager=new HttpManager();
                }
            }
        }
        return httpManager;
    }
//    private ServiceApi serviceApi;
    private TongPaoApi tongpaoApi;

    private Retrofit getRetrofit(String url){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOk())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient getOk() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            return chain.proceed(request);
        }
    }


    private class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request build = chain.request().newBuilder()
                    .addHeader("Authorization", "APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    .build();
            return chain.proceed(build);
        }
    }
    /**
     * ServiceApi
     * @return
     */
//    public ServiceApi getServiceApi(){
//        if(serviceApi==null){
//            serviceApi=getRetrofit(ServiceApi.BASE_URL).create(ServiceApi.class);
//        }
//        return serviceApi;
//    }
    /**
     * TongpaoApi
     * @return
     */
    public TongPaoApi getTongpaoApi(){
        if(tongpaoApi==null){
            tongpaoApi=getRetrofit(TongPaoApi.BASE_URL).create(TongPaoApi.class);
        }
        return tongpaoApi;
    }

}
