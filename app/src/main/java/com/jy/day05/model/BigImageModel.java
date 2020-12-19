package com.jy.day05.model;

import com.jy.day05.app.Constants;
import com.jy.day05.base.BaseModel;
import com.jy.day05.interfaces.CallBack;
import com.jy.day05.interfaces.tongpao.IBigImage;
import com.jy.day05.interfaces.tongpao.IDown;
import com.jy.day05.interfaces.tongpao.IRecommend;
import com.jy.day05.net.CommonSubscriber;
import com.jy.day05.net.HttpManager;
import com.jy.day05.utils.ImageLoader;
import com.jy.day05.utils.RxUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class BigImageModel extends BaseModel implements IBigImage.Model, IDown.DownModel {
    @Override
    public void getBigImage() {

    }

    @Override
    public void downImage(String url, CallBack callBack) {
        String[] arr = ImageLoader.splitUrl(url);
        String baseUrl = arr[0];
        String imgName = arr[1];
        String path = arr[2];
        Disposable disposable = HttpManager.getHttpManager().getImageApi(baseUrl)
                .downImage(url)
                .compose(RxUtils.rxScheduler())
                 .subscribeWith(new CommonSubscriber<ResponseBody>(callBack) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        FileOutputStream fileOutputStream = null;
                        //拿到流写入本地
                        try {
                            //判断当前的流是否有数据
                            if(inputStream.available() > 0){
                                //判断当前本地的路径是否存在
                                File file = new File(Constants.PATH_IMGS);
                                if(file.isDirectory() && !file.exists()){
                                    boolean bool = file.createNewFile();
                                    if(bool){
                                        fileOutputStream = new FileOutputStream(path);
                                        int n = 0;
                                        byte[] bytes = new byte[4096];
                                        while((n=inputStream.read(bytes)) != -1){
                                            fileOutputStream.write(bytes);
                                        }
                                        fileOutputStream.flush(); //刷新到sd卡
                                    }else{
                                        callBack.onFaile("创建本地目录失败");
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                inputStream.close();
                                fileOutputStream.close();
                                callBack.onSuccess(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        addDisposable(disposable);
    }
}
