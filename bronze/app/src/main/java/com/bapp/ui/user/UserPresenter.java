package com.bapp.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import com.bapp.R;
import com.bapp.base.BaseActivity;
import com.bapp.base.BasePresenter;
import com.bapp.entity.UserInfo;
import com.bapp.ui.search.SearchActivity;


/**
 * @author BuMingYang
 * @des
 */
public class UserPresenter extends BasePresenter<UserView> {

    private UserInfo mUserInfo;
    private Context mContext;

    public UserPresenter(BaseActivity context) {

        super(context);
        this.mContext = context;
    }


    /**
     * 获取用户信息
     */
    public void loadUserInfo() {

        mUserInfo = new UserInfo();
        mUserInfo.userName = "卜鸣扬";
        mUserInfo.userAccount = "MingYang_X";

        fillView();


    }

    /***
     * 跳转WebActivity
     * @return
     */

    public void jumpWebActivity() {

//        WebActivity.runActivity(mContext,"简书", Apis.JIANSHU);

    }

    public UserInfo getUserInfo() {

        return null != mUserInfo ? mUserInfo : null;
    }

    /**
     * 填充数据
     */
    private void fillView() {

        if (mUserInfo != null) {

            getView().getAccount().setText(String.format(mContext.getString(R.string.my_chat_account), mUserInfo.userAccount));
            getView().getName().setText(mUserInfo.userName);
        }

    }

    public void jumpSearch() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val options = activity ?.let {
//                ActivityOptionsCompat.makeSceneTransitionAnimation(it, iv_search, iv_search.transitionName)
//            }
//            startActivity(Intent(activity, SearchActivity:: class.java),options ?.toBundle())

//            ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(this,
//                    getView().getSearch(), R.anim.translate_none);
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,
                    getView().getSearch(), getView().getSearch().getTransitionName());

            ActivityCompat.startActivity(mContext,
                    new Intent(mContext, SearchActivity.class), compat.toBundle());


        } else {
            SearchActivity.runActivity(mContext);
        }
    }

}
