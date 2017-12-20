package com.haha.simplenews.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by Admin on 2017/3/27.
 */

public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenter> extends RxFragment {

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.stop();
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    protected abstract T createPresenter();

    /**
     * 绑定生命周期
     */
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
