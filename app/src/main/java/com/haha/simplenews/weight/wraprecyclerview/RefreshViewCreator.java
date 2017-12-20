package com.haha.simplenews.weight.wraprecyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 格格不入 on 2017/12/18.
 *
 * 下拉刷新的辅助类为了匹配所有效果
 */

public abstract class RefreshViewCreator {
    //获取下拉刷新的View
    public abstract View getRefreshView(Context context,ViewGroup Parent);
    //正在下拉
    public abstract void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus);
    //刷新
    public abstract void onRefresh();
    //停止刷新
    public abstract void onStopRefresh();
}
