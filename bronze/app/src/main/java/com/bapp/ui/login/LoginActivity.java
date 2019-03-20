package com.bapp.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.bapp.R;
import com.bapp.base.BaseActivity;
import com.bapp.base.BasePresenter;
import com.facebook.appevents.AppEventsLogger;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author BuMingYang
 * @des 测试三方登录
 */
public class LoginActivity extends BaseActivity implements GoogleLogin.GoogleListener {


    @BindView(R.id.login_result)
    TextView mLoginResult;


    private FaceBookLogin faceBookLogin = null;
    private GoogleLogin googleLogin = null;

    private IWXAPI mWxApi = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        faceBookLogin = new FaceBookLogin(this);
        faceBookLogin.setFacebookListener(object -> mLoginResult.setText("facebook_account_oauth_Success !"));

        googleLogin = new GoogleLogin(this);
        googleLogin.setGoogleListener(this);

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

        googleLogin.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {

        faceBookLogin.login();

    }

    @OnClick(R.id.login_google)
    public void onGoogleClick() {
        googleLogin.signIn();
    }

    @OnClick(R.id.login_wx)
    public void onLoginWx() {

        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, "", false);
        // 将该app注册到微信
        mWxApi.registerApp("");

        //先判断是否安装微信APP,按照微信的说法，目前移动应用上微信登录只提供原生的登录方式，需要用户安装微信客户端才能配合使用。
        if (!mWxApi.isWXAppInstalled()) {
            Toast.makeText(this, "您还未安装微信客户端", Toast.LENGTH_LONG).show();
            return;
        }
        //微信登录

        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        //像微信发送请求
        mWxApi.sendReq(req);


    }

    @Override
    public void onGoogleSuccess(@NotNull String id) {

        Toast.makeText(this, "id=" + id, Toast.LENGTH_LONG).show();

    }
}
