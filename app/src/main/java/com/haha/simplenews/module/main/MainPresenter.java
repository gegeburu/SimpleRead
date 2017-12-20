package com.haha.simplenews.module.main;

import android.util.Log;

import com.haha.simplenews.api.GankApi;
import com.haha.simplenews.bean.PhotoBean;
import com.haha.simplenews.global.GlobalConfig;
import com.haha.simplenews.utils.RetrofitFactory;
import com.haha.simplenews.utils.SpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 格格不入 on 2017/12/11.
 */

public class MainPresenter extends MainContract.Presenter{
    @Override
    public void getImageToCache() {
        RetrofitFactory.getRetrofit(GankApi.HOST)
                .create(GankApi.class)
                .getRandomBeauties(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getView().<PhotoBean>bindToLife())
                .subscribe(new Observer<PhotoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhotoBean meiziResult) {
                        String url = meiziResult.results.get(0).url;
                        Log.e("url", "onNext: "+url);
                        if (meiziResult != null && meiziResult.results != null && meiziResult.results.size() > 0 &&  url!= null) {
                            SpUtils.setString(GlobalConfig.LAUNCH_IMG_URL,url);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void stop() {

    }
}
