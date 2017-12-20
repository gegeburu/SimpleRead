package com.haha.simplenews.module.launch;

import com.haha.simplenews.base.BasePresenter;
import com.haha.simplenews.base.BaseView;

/**
 * Created by 格格不入 on 2017/12/11.
 *
 * 欢迎界面契约类
 */

public interface LaunchContract {

    interface View extends BaseView{
        /**
         * 展示图片
         */
        void showImage();
    }
    abstract class Presenter extends BasePresenter<View>{
        /**
         * 获取图片url
         * @return
         */
        public abstract String getImageUrl();

    }
}
