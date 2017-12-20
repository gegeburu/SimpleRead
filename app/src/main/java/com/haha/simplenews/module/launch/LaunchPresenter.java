package com.haha.simplenews.module.launch;

import com.haha.simplenews.global.GlobalConfig;
import com.haha.simplenews.utils.SpUtils;

/**
 * Created by 格格不入 on 2017/12/11.
 */

public class LaunchPresenter extends LaunchContract.Presenter {
    @Override
    public String getImageUrl() {
        return SpUtils.getString(GlobalConfig.LAUNCH_IMG_URL,"");
    }

    @Override
    public void stop() {

    }
}
