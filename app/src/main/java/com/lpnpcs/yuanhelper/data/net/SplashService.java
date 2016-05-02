package com.lpnpcs.yuanhelper.data.net;

import com.lpnpcs.yuanhelper.data.entity.SplashEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：splash网络请求
 */
public interface SplashService {
    @GET("start-image/1080*1920")
    Observable<SplashEntity> getSplash();

}
