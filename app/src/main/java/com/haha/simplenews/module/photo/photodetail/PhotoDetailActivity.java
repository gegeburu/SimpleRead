package com.haha.simplenews.module.photo.photodetail;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.haha.simplenews.MyApplication;
import com.haha.simplenews.R;
import com.haha.simplenews.base.BaseActivity;
import com.haha.simplenews.bean.PhotoBean;
import com.haha.simplenews.global.GlobalConfig;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;

/**
 * Created by 格格不入 on 2017/12/20.
 */

public class PhotoDetailActivity extends BaseActivity{
    @ViewById(R.id.pv_pic)
    PhotoView mPvPic;
    private PhotoBean.ResultsBean photoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photodetail);
        ViewUtils.inject(this);
        initData();
        initView();
    }

    private void initView() {
        Glide.with(MyApplication.getContext())
                .load(photoBean.url)
                .fitCenter()
                .crossFade()
                .into(mPvPic);
    }

    private void initData() {
        photoBean = getIntent().getParcelableExtra(GlobalConfig.PHOTO_OBJECT);
    }
}
