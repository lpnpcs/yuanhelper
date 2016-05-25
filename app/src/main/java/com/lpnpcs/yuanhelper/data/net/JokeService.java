package com.lpnpcs.yuanhelper.data.net;

import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public interface JokeService {
    @GET("xiaohua.json")
    Observable<ArrayList<JokeEntity>> getJoke();

    @GET("tupian.json")
    Observable<ArrayList<ImageEntity>> getJokeImage();

}
