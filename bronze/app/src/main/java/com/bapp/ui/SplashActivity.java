package com.bapp.ui;

import android.content.Intent;
import android.widget.ImageView;

import com.bapp.R;
import com.bapp.base.BaseActivity;
import com.bapp.base.BasePresenter;
import com.bapp.ui.login.LoginActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class SplashActivity extends BaseActivity {


    @BindView(R.id.image_guide)
    ImageView mImageView;

    @Override
    public void init() {

//        ToolBarUtils.isChangeThemeStyle(this,true);

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

        return R.layout.activity_guide;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initData() {

        Glide.with(this)
                .load(R.mipmap.ic_main_head)
                .apply(bitmapTransform(new BlurTransformation( 25, 4)))
                .into(mImageView);

    }

    @Override
    public void initListener() {

        mImageView.postDelayed(() -> {

//            jumpToActivity(MainActivity.class);
            jumpToActivity(LoginActivity.class);

        }, 1000);


    }

    private void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //TODO  此处疑问 使用lambda 表达式之后 如何关心 removeCallback/

    }

}
