package com.lpnpcs.yuanhelper.data.net;

import com.lpnpcs.yuanhelper.data.entity.SplashEntity;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;
import com.lpnpcs.yuanhelper.util.API;
import com.lpnpcs.yuanhelper.util.LogUtil;

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

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private SplashService splashService;
    private ZhiHuService zhiHuService;

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


        splashService = retrofit.create(SplashService.class);
        zhiHuService = retrofit.create(ZhiHuService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }


   public void  getSplash(Subscriber<String> subscriber){
       LogUtil.d("lp"," sss11");
       splashService.getSplash().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .map(new Func1<SplashEntity, String>() {
                   @Override
                   public String call(SplashEntity entity) {

                       try {
                           LogUtil.d("lp"," s"+entity.getImg());

                           return entity.getImg();
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                       return null;
                   }
               })
               .subscribe(subscriber);
   }

    public  void  getZhiHuLatest(Subscriber<ZhiHuEntity> subscriber){
        zhiHuService.getZhiHuLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getZhiHuBefore(Subscriber<ZhiHuEntity> subscriber,String date){
        zhiHuService.getZhiHubefore(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

