package com.haha.simplenews.module.news.newsdetail;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haha.simplenews.MyApplication;
import com.haha.simplenews.R;
import com.haha.simplenews.base.BaseActivity;
import com.haha.simplenews.base.MVPBaseActivity;
import com.haha.simplenews.bean.NewsListBean;
import com.haha.simplenews.global.GlobalConfig;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;
import com.haha.simplenews.weight.NestedScrollWebView;

/**
 * Created by 格格不入 on 2017/12/20.
 */

public class NewsDetailActivity extends BaseActivity{

    @ViewById(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @ViewById(R.id.iv_detail)
    ImageView ivDetail;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @ViewById(R.id.app_bar)
    AppBarLayout appBar;
    @ViewById(R.id.tv_detail_copyright)
    TextView tvDetailcopyright;
    @ViewById(R.id.wv_news)
    NestedScrollWebView mWvNews;
    private NewsListBean.NewsBean newsBean;

    private int downX, downY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        ViewUtils.inject(this);
        initData();
        initView();
    }

    public void initView() {
        initToolBar();
//声明WebSettings子类
        WebSettings webSettings = mWvNews.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);


//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        mWvNews.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        tvDetailTitle.setText(newsBean.getTitle());
        tvDetailcopyright.setText(newsBean.getSource());
        Glide.with(MyApplication.getContext()).load(newsBean.getImgsrc()).crossFade().into(ivDetail);

        mWvNews.loadUrl(newsBean.getUrl());

//        mWvNews.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                downX = (int) motionEvent.getX();
//                downY = (int) motionEvent.getY();
//                return false;
//            }
//        });
    }

    public void initData() {
        newsBean = getIntent().getParcelableExtra(GlobalConfig.NEWS_OBJECT);

    }
    public void initToolBar(){
        toolbar.setTitle("新闻详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
