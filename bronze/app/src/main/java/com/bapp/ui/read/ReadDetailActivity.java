package com.bapp.ui.read;

import android.content.Context;
import android.content.Intent;

import com.bapp.R;
import com.bapp.base.BaseActivity;


public class ReadDetailActivity extends BaseActivity<DetailView, DetailPresenter> implements DetailView {

    public static void runActivity(Context context){

        Intent intent = new Intent(context,ReadDetailActivity.class);
        context.startActivity(intent);
    }

    /**
     * 开始请求
     */
    @Override
    protected void start() {

    }

    /**
     * 绑定布局ID
     *
     * @return
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_read_detail;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter(this);
    }
}
