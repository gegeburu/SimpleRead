package com.haha.simplenews.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by 格格不入 on 2017/11/6.
 * View基类
 */

public interface  BaseView {
    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
//    void showError();
//
//    void complete();
}
