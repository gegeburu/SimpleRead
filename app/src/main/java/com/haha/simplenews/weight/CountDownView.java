package com.haha.simplenews.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 格格不入 on 2017/12/12.
 *
 * 倒计时View
 */

public class CountDownView extends AppCompatTextView {

    private Timer mTimer;
    private int mDuration = 4;
    private OnFinishListener mListener;

    public CountDownView(Context context) {
        this(context,null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 开始倒计时
     * @param duration
     */
    public void start(int duration){
        mDuration = duration;
        setText("跳过("+mDuration+")");
        mTimer = new Timer();
        TimerTask CountDownTask = new TimerTask() {
            @Override
            public void run() {
                mDuration--;
                post(new Runnable() {
                    @Override
                    public void run() {
                        setText("跳过("+mDuration+")");
                        Log.e("mDuration", "run: "+mDuration);
                    }
                });
                if (mDuration == 0){
                    mTimer.cancel();
                    if (mListener != null){
                        post(new Runnable() {
                            @Override
                            public void run() {
                                mListener.onFinish();
                            }
                        });
                    }
                }
            }
        };
        mTimer.schedule(CountDownTask,1000,1000);
    }
    //设置结束的监听
    public void setOnFinishListener(OnFinishListener listener){
        this.mListener = listener;
    }

    public interface OnFinishListener{
        void onFinish();
    }
    public void stop(){
        mTimer.cancel();
    }

}
