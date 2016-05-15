package com.lpnpcs.yuanhelper.data.net;

import com.lpnpcs.yuanhelper.data.entity.ZhiHuDetailEntity;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：RetrofitService for ZhiHu
 */
public interface ZhiHuService {
    @GET("news/latest")
    Observable<ZhiHuEntity> getZhiHuLatest();

    @GET("news/before/{date}")
    Observable<ZhiHuEntity> getZhiHuBefore(@Path("date") String date);

    @GET("news/{id}")
    Observable<ZhiHuDetailEntity> getZhiHuDetail(@Path("id") String id);
}
