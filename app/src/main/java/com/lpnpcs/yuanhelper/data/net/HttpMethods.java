package com.lpnpcs.yuanhelper.data.net;

import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;
import com.lpnpcs.yuanhelper.data.entity.SplashEntity;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuDetailEntity;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;
import com.lpnpcs.yuanhelper.util.API;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：封装retrofit请求
 */
public class HttpMethods {
    public static final String BASE_ZHI_URL = API.ZhiBase;
    public static final String BASE_JOKE = API.JOKE_BASE;

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private Retrofit retrofit_joke;
    private SplashService splashService;
    private ZhiHuService zhiHuService;
    private  JokeService jokeService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_ZHI_URL)
                .build();
        retrofit_joke = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_JOKE)
                .build();

        splashService = retrofit.create(SplashService.class);
        zhiHuService = retrofit.create(ZhiHuService.class);
        jokeService = retrofit_joke.create(JokeService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取首页图标
     * @param subscriber
     */
   public void  getSplash(Subscriber<String> subscriber){
       splashService.getSplash().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .map(new Func1<SplashEntity, String>() {
                   @Override
                   public String call(SplashEntity entity) {

                       try {
                           return entity.getImg();
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                       return null;
                   }
               })
               .subscribe(subscriber);
   }

    /**
     * 获取知乎最近新闻
     * @param subscriber
     */
    public  void  getZhiHuLatest(Subscriber<ZhiHuEntity> subscriber){
        zhiHuService.getZhiHuLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取知乎历史新闻
     * @param subscriber
     * @param date
     */
    public void getZhiHuBefore(Subscriber<ZhiHuEntity> subscriber,String date){
        zhiHuService.getZhiHuBefore(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取知乎详情
     * @param subscriber
     * @param id
     */
    public void getZhiHuDetail(Subscriber<ZhiHuDetailEntity> subscriber,String id){
        zhiHuService.getZhiHuDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 获取笑话
     * @param subscriber
     */
    public void getJoke(Subscriber<ArrayList<JokeEntity>> subscriber){
        jokeService.getJoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取搞笑图片
     * @param subscriber
     */
    public  void getJokeImage(Subscriber<ArrayList<ImageEntity>> subscriber){
        jokeService.getJokeImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }




}

