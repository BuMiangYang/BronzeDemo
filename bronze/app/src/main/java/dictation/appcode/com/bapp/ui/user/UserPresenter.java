package dictation.appcode.com.bapp.ui.user;

import android.content.Context;

import dictation.appcode.com.bapp.R;
import dictation.appcode.com.bapp.base.BaseActivity;
import dictation.appcode.com.bapp.base.BasePresenter;
import dictation.appcode.com.bapp.entity.UserInfo;

/**
 * 描述：我
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

    public  void jumpWebActivity(){

//        WebActivity.runActivity(mContext,"简书", Apis.JIANSHU);

    }
    public UserInfo getUserInfo() {

        return null != mUserInfo ? mUserInfo : null;
    }

    /**
     * 填充数据
     */
    private void fillView(){



        if (mUserInfo != null) {

            getView().getAccount().setText(String.format(mContext.getString(R.string.my_chat_account),mUserInfo.userAccount));
            getView().getName().setText(mUserInfo.userName);
        }

    }

}
