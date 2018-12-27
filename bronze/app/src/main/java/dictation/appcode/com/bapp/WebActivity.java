/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dictation.appcode.com.bapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import dictation.appcode.com.bapp.base.BaseActivity;
import dictation.appcode.com.bapp.base.BasePresenter;

/**

 */
public class WebActivity extends BaseActivity {

    private String mUrl;
    private String mTitle;
    private Intent mIntent;
    private Bundle mExtras;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.pb) ProgressBar pb;
    @BindView(R.id.webView) WebView webView;

    private boolean isLoading = false;


    @Override
    public void init() {

        //得到url
        try {
            mIntent = getIntent();
            mExtras = mIntent.getExtras();
            if (mExtras == null) {
                finish();
                return;
            }
            mUrl = mExtras.getString("url");
            if (TextUtils.isEmpty(mUrl)) {
                finish();
                return;
            }
            mTitle = mExtras.getString("title");
        } catch (Exception e) {
            e.printStackTrace();
            finish();
            return;
        }

    }

    /**
     * 开始请求
     */
    @Override
    protected void start() {

    }

    @Override
    public void initData() {


        pb.setMax(100);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if(pb != null){

                    pb.setProgress(newProgress);
                    if (newProgress >= 100) {
                        pb.setVisibility(View.GONE);
                    }
                }


            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(mUrl);    }

    /**  Toolbar */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * 绑定布局ID
     *
     * @return
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onBackPressed() {
        isLoading = false;

        //如果当前浏览器可以后退，则后退上一个页面
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.removeAllViews();
            try {
                webView.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
            webView = null;
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //在自己浏览器中跳转
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            isLoading = true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            isLoading = false;
        }
    }

}
