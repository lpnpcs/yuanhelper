package com.lpnpcs.yuanhelper.data.net;

import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public interface JokeService {
    @GET("xiaohua.json")
    Observable<JokeEntity> getJoke();

    @GET("tupian.json")
    Observable<ImageEntity> getJokeImage();

}
