package com.haha.simplenews.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.haha.simplenews.R;
import com.haha.simplenews.weight.wraprecyclerview.RefreshViewCreator;

/**
 * Created by 格格不入 on 2017/12/19.
 */

public class MyRefreshViewCreator extends RefreshViewCreator{
    private ImageView ivRefreshHeader;

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.layout_refresh_header, parent, false);
        ivRefreshHeader = refreshView.findViewById(R.id.iv_refresh_header);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        float rotate = ((float) currentDragHeight) / refreshViewHeight;
        // 不断下拉的过程中不断的旋转图片
        ivRefreshHeader.setRotation(rotate * 360);
    }

    @Override
    public void onRefresh() {
        // 刷新的时候不断旋转
        RotateAnimation animation = new RotateAnimation(0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setRepeatCount(-1);
        animation.setDuration(1000);
        ivRefreshHeader.startAnimation(animation);

    }

    @Override
    public void onStopRefresh() {
        // 停止加载的时候清除动画
        ivRefreshHeader.setRotation(0);
        ivRefreshHeader.clearAnimation();
    }
}
