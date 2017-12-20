package com.haha.simplenews.api;

import com.haha.simplenews.bean.NewsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 格格不入 on 2017/12/14.
 */

public interface WangYiApi {
    String HOST = "http://c.m.163.com/";
    //头条
    @GET("/nc/article/headline/T1348647909107/{page}-20.html")
    Observable<NewsListBean> getHeadLineList(@Path("page") int page);
    //NBA
    @GET("/nc/article/headline/T1348649145984/{page}-20.html")
    Observable<NewsListBean> getNBAList(@Path("page") int page);
    //汽车
    @GET("/nc/article/headline/T1348654060988/{page}-20.html")
    Observable<NewsListBean> getCarList(@Path("page") int page);
    //笑话
    @GET("/nc/article/headline/T1350383429665/{page}-20.html")
    Observable<NewsListBean> getJokeList(@Path("page") int page);
}
