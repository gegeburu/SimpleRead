package com.haha.simplenews.utils.findViewUtil;

import android.app.Activity;
import android.view.View;

/**
 * Created by 格格不入 on 2017/12/11.
 *
 * findViewById类
 */

public class ViewFinder {
    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity){
        this.mActivity = activity;
    }
    public ViewFinder(View view){
        this.mView = view;
    }

    public View findViewById(int id){
        return null == mActivity?mView.findViewById(id):mActivity.findViewById(id);
    }
}
