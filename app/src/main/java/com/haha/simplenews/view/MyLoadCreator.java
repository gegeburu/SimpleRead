package com.haha.simplenews.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.haha.simplenews.R;
import com.haha.simplenews.weight.wraprecyclerview.LoadRefreshRecyclerView;
import com.haha.simplenews.weight.wraprecyclerview.LoadViewCreator;


public class MyLoadCreator extends LoadViewCreator {
    // 加载数据的ImageView
    private TextView mLoadTv;

    @Override
    public View getLoadView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.layout_load_footer_view, parent, false);
        mLoadTv = (TextView) refreshView.findViewById(R.id.load_tv);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        if (currentRefreshStatus == LoadRefreshRecyclerView.LOAD_STATUS_PULL_DOWN_REFRESH) {
            mLoadTv.setText("上拉加载更多");
        }
        if (currentRefreshStatus == LoadRefreshRecyclerView.LOAD_STATUS_LOOSEN_LOADING) {
            mLoadTv.setText("松开加载更多");
        }
    }

    @Override
    public void onLoading() {
        mLoadTv.setText("正在加载更多");

    }

    @Override
    public void onStopLoad() {
        // 停止加载的时候清除动画
        mLoadTv.setText("上拉加载更多");
        mLoadTv.setVisibility(View.VISIBLE);
    }
}
