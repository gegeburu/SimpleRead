package com.haha.simplenews.base;

import java.lang.ref.WeakReference;

/**
 * Created by 格格不入 on 2017/11/6.
 * presenter基类
 */

public abstract class BasePresenter<T extends BaseView> {

    protected WeakReference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    protected T getView() {
        return mViewRef.get();
    }
    public abstract void stop();


}
