package com.bapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.classic.common.MultipleStatusView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author bmy
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    public Unbinder unbinder;

    /**
     * 多种状态的 View 的切换
     */
    protected MultipleStatusView mLayoutStatusView = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            //因为之后所有的子类都要实现对应的View接口 绑定view
            mPresenter.attachView((V) this);
        }

        setContentView(provideContentViewId());

        unbinder = ButterKnife.bind(this);

        initView();
        initData();
        initListener();


    }

    public void initListener() {
        if(null != mLayoutStatusView ){
            mLayoutStatusView.setOnClickListener(mRetryClickListener);
        }
    }



    View.OnClickListener mRetryClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            start();
        }
    };



    public void initData() {
    }

    public void initView() {
    }

    /**
     * 在setContentView()调用之前调用
     * 可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
     */
    public void init() {

    }

    /**
     * 开始请求
     */
    protected abstract void start();

    /**
     * 绑定布局ID
     *
     * @return
     */
    protected abstract int provideContentViewId();

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    protected abstract T createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            // 解绑view
            mPresenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
