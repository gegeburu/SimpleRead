package com.haha.simplenews.module.launch;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haha.simplenews.R;
import com.haha.simplenews.base.MVPBaseActivity;
import com.haha.simplenews.module.main.MainActivity;
import com.haha.simplenews.utils.findViewUtil.OnClick;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;
import com.haha.simplenews.weight.CountDownView;

/**
 * Created by 格格不入 on 2017/12/11.
 */

public class LaunchActivity extends MVPBaseActivity<LaunchContract.View,
        LaunchContract.Presenter> implements LaunchContract.View {

    @ViewById(R.id.countdown)
    private CountDownView mCountDown;
    @ViewById(R.id.iv_welcome)
    private ImageView mIvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ViewUtils.inject(this);

        initView();
    }

    private void initView() {
        //展示图片
        showImage();
        //开启倒计时
        mCountDown.start(4);
        //设置倒计时监听
        mCountDown.setOnFinishListener(new CountDownView.OnFinishListener() {
            @Override
            public void onFinish() {
                startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    @OnClick(R.id.countdown)
    public void onClick(){
        mCountDown.stop();
        startActivity(new Intent(LaunchActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void showImage() {
        String imageUrl = mPresenter.getImageUrl();
        if (TextUtils.isEmpty(imageUrl)){
            mIvWelcome.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else {
            Glide.with(this).load(imageUrl).into(mIvWelcome);
        }
    }

    @Override
    protected LaunchContract.Presenter createPresenter() {
        return new LaunchPresenter();
    }

    @Override
    protected void onDestroy() {
        mCountDown.stop();
        super.onDestroy();
    }
}
