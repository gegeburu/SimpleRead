package com.haha.simplenews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.security.auth.login.LoginException;

/**
 * Created by 格格不入 on 2017/12/14.
 */

public abstract class LazyLoadFragment<V extends BaseView,T extends BasePresenter> extends MVPBaseFragment<V,T>{

    protected boolean isViewInitiated;
    protected boolean isDataInitiated;
    protected boolean isVisibleToUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater,container,savedInstanceState);
        isViewInitiated = true;
        LazyLoadData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        LazyLoadData();

    }

    protected void LazyLoadData(){
        //如果View加载完毕并且第一次加载数据并且可见时
        if (isVisibleToUser && isViewInitiated && !isDataInitiated){
            Log.e("加载", "LazyLoadData: "+"加载");
            initData();
            isDataInitiated = true;
        }
    }

    protected abstract void initData();

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
