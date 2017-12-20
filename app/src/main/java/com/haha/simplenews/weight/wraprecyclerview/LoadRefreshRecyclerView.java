package com.haha.simplenews.weight.wraprecyclerview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 格格不入 on 2017/12/19.
 */

public class LoadRefreshRecyclerView extends RefreshRecyclerView{
    // 上拉加载更多的辅助类
    private LoadViewCreator mLoadCreator;
    // 上拉加载更多头部的高度
    private int mLoadViewHeight = 0;
    // 上拉加载更多的头部View
    private View mLoadView;
    // 手指按下的Y位置
    private int mFingerDownY;
    // 当前是否正在拖动
    private boolean mCurrentDrag = false;
    // 当前的状态
    private int mCurrentLoadStatus;
    // 默认状态
    public int LOAD_STATUS_NORMAL = 10;
    // 上拉加载更多状态
    public static int LOAD_STATUS_PULL_DOWN_REFRESH = 11;
    // 松开加载更多状态
    public static int LOAD_STATUS_LOOSEN_LOADING = 12;
    // 正在加载更多状态
    public int LOAD_STATUS_LOADING = 13;
    public LoadRefreshRecyclerView(Context context) {
        super(context);
    }

    public LoadRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void addLoadViewCreator(LoadViewCreator loadCreator){
        this.mLoadCreator = loadCreator;
        addLoadView();
    }
    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addLoadView();
    }

    //添加下拉刷新View
    private void addLoadView() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter != null && mLoadCreator != null) {
            View loadView = mLoadCreator.getLoadView(getContext(), this);
            if (loadView != null) {
                addFooterView(loadView);
                this.mLoadView = loadView;
            }
        }
    }
    //设置刷新View的marginBottom
    public void setLoadViewMarginBottom(int marginBottom) {
        MarginLayoutParams params = (MarginLayoutParams) mLoadView.getLayoutParams();
        if (marginBottom < 0) {
            marginBottom = 0;
        }
        params.bottomMargin = marginBottom;
        mLoadView.setLayoutParams(params);
    }


    //判断是不是滚动到了最顶部，这个是从SwipeRefreshLayout里面copy过来的源代码
    public boolean canScrollDown() {
        return ViewCompat.canScrollVertically(this, 1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置 ,之所以写在dispatchTouchEvent那是因为如果我们处理了条目点击事件，
                // 那么就不会进入onTouchEvent里面，所以只能在这里获取
                mFingerDownY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (mCurrentDrag) {
                    restoreRefreshView();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    //重置当前刷新状态
    private void restoreRefreshView() {
        int currentBottomMargin = ((MarginLayoutParams) mLoadView.getLayoutParams()).bottomMargin;
        int finalBottomMargin = 0;
        if (mCurrentLoadStatus == LOAD_STATUS_LOOSEN_LOADING) {
            mCurrentLoadStatus = LOAD_STATUS_LOOSEN_LOADING;
            if (mLoadCreator != null) {
                mLoadCreator.onLoading();
            }
            if (mListener != null) {
                mListener.onLoad();
            }
        }
        int distance = currentBottomMargin - finalBottomMargin;
        //回弹到指定位置
        ValueAnimator animator = ObjectAnimator.ofFloat(currentBottomMargin, finalBottomMargin).setDuration(distance);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentBottomMargin = (float) valueAnimator.getAnimatedValue();
                setLoadViewMarginBottom((int) currentBottomMargin);
            }
        });
        animator.start();
        mCurrentDrag = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //没有到达最底部或者正在刷新状态不做处理
                if (canScrollDown() || mCurrentLoadStatus == LOAD_STATUS_LOADING
                        || mLoadCreator == null || mLoadView == null) {

                    return super.onTouchEvent(e);
                }

                if (mLoadCreator != null) {
                    mLoadViewHeight = mLoadView.getMeasuredHeight();
                }
                // 解决上拉加载更多自动滚动问题
                if (mCurrentDrag) {
                    scrollToPosition(getAdapter().getItemCount() - 1);
                }
                //获取手指拖拽的距离
                int distanceY = (int) ((e.getRawY() - mFingerDownY) * mDragIndex);

                if (distanceY < 0) {
                    setLoadViewMarginBottom(-distanceY);
                    updateLoadStatus(-distanceY);
                    mCurrentDrag = true;
                    return true;
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    //更新刷新的状态
    private void updateLoadStatus(int distanceY) {
        if (distanceY <= 0) {
            mCurrentLoadStatus = LOAD_STATUS_NORMAL;
        } else if (distanceY < mLoadViewHeight) {
            mCurrentLoadStatus = LOAD_STATUS_PULL_DOWN_REFRESH;
        } else {
            //松开刷新状态
            mCurrentLoadStatus = LOAD_STATUS_LOOSEN_LOADING;
        }
        if (mLoadCreator != null) {
            mLoadCreator.onPull(distanceY, mLoadViewHeight, mCurrentLoadStatus);
        }
    }

    //停止加载更多
    public void onStopLoad() {
        mCurrentLoadStatus = LOAD_STATUS_NORMAL;
        restoreRefreshView();
        if (mLoadCreator != null) {
            mLoadCreator.onStopLoad();
        }
    }
    // 处理加载更多回调监听
    private OnLoadMoreListener mListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoad();
    }

}
