package com.haha.simplenews.weight.wraprecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 格格不入 on 2017/12/15.
 */

public class WrapRecyclerView extends RecyclerView {

    private WrapRecyclerAdapter mWrapAdapter;
    // 这个是列表数据的Adapter
    private RecyclerView.Adapter mAdapter;


    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            this.mAdapter = adapter;
            //在这里替换成WrapRecyclerAdapter;
            mWrapAdapter = new WrapRecyclerAdapter(adapter);
            super.setAdapter(mWrapAdapter);
            mWrapAdapter.adjustSpanSize(this);
        }
    }

    //添加头部
    public void addHeaderView(View view) {
        if (mWrapAdapter != null) {
            mWrapAdapter.addHeaderView(view);
        }
    }

    //添加底部
    public void addFooterView(View view) {
        if (mWrapAdapter != null) {
            mWrapAdapter.addFooterView(view);
        }
    }

    //移除底部
    public void removeHeaderView(View view) {
        if (mWrapAdapter != null) {
            mWrapAdapter.removeHeaderView(view);
        }
    }

    //移除底部
    public void removeFooterView(View view) {
        if (mWrapAdapter != null) {
            mWrapAdapter.removeFooterView(view);
        }
    }
}
