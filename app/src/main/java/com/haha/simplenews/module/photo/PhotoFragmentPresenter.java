package com.haha.simplenews.module.photo;

import android.util.Log;

import com.haha.simplenews.api.GankApi;
import com.haha.simplenews.bean.PhotoBean;
import com.haha.simplenews.utils.RetrofitFactory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 格格不入 on 2017/12/19.
 */

public class PhotoFragmentPresenter extends PhotoFragmentContract.Presenter{
    @Override
    public void stop() {

    }

    @Override
    public void LoadDataFromNet(final int page) {
        RetrofitFactory.getRetrofit(GankApi.HOST)
                .create(GankApi.class)
                .getPhoto(page)
                .subscribeOn(Schedulers.io())
                .map(new Function<PhotoBean, List<PhotoBean.ResultsBean>>() {
                    @Override
                    public List<PhotoBean.ResultsBean> apply(PhotoBean photoBean) throws Exception {
                        return photoBean.results;
                    }
                })
                .compose(getView().<List<PhotoBean.ResultsBean>>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoBean.ResultsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PhotoBean.ResultsBean> resultsBeans) {
                        Log.e("page", "onNext: "+page);
                        if (page == 1){
                            getView().showData(resultsBeans);
                        }else {
                            getView().showLoadMore(resultsBeans);
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
}
