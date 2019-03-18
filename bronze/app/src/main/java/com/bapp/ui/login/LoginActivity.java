package com.bapp.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bapp.R;
import com.bapp.base.BaseActivity;
import com.bapp.base.BasePresenter;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author BuMingYang
 * @des 测试三方登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_button)
    LoginButton mLoginButton;

    @BindView(R.id.login_result)
    TextView mLoginResult;


    private FaceBookLogin faceBookLogin = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        faceBookLogin = new FaceBookLogin(this);

        faceBookLogin.setFacebookListener(new FaceBookLogin.FacebookListener() {
            @Override
            public void facebookLoginSuccess(JSONObject object) {

                mLoginResult.setText("facebook_account_oauth_Success !");

            }

            @Override
            public void facebookLoginFail(String message) {

                mLoginResult.setText("facebook_account_oauth_Fail !" + message);

            }

            @Override
            public void facebookLoginCancel() {

                mLoginResult.setText("facebook_account_oauth_Cancel !");

            }
        });

    }


    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();

    }


    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

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
        return R.layout.login_layout;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        faceBookLogin.getCallbackManager().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {

        faceBookLogin.login();

    }
}
