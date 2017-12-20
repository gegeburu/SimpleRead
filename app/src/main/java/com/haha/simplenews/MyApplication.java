package com.haha.simplenews;

import android.app.Application;
import android.content.Context;

/**
 * Created by 格格不入 on 2017/12/11.
 */

public class MyApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
