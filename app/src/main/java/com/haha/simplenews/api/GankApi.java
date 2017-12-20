package com.haha.simplenews.api;

import com.haha.simplenews.bean.PhotoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 格格不入 on 2017/12/11.
 */

public interface GankApi {
    String HOST = "http://gank.io/api/";

    //获取Launch图片
    @GET("random/data/福利/{number}")
    Observable<PhotoBean> getRandomBeauties(@Path("number") int number);
    //获取图片
    @GET("data/福利/10/{page}")
    Observable<PhotoBean> getPhoto(@Path("page") int pege);
}
