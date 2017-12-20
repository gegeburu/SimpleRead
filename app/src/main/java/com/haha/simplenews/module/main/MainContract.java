package com.haha.simplenews.module.main;

import com.haha.simplenews.base.BasePresenter;
import com.haha.simplenews.base.BaseView;

/**
 * Created by 格格不入 on 2017/12/11.
 * 主页契约类
 *
 */

public interface MainContract {

    interface View extends BaseView{
        void cacheImage();
    }
    abstract class Presenter extends BasePresenter<View>{
        public abstract void getImageToCache();
    }
}
