package dictation.appcode.com.bapp.ui.user;

import android.widget.TextView;

import butterknife.BindView;
import dictation.appcode.com.bapp.R;
import dictation.appcode.com.bapp.base.BaseFragment;
import dictation.appcode.com.bapp.ui.MainActivity;

/**
 * 描述：
 */

public class UserIndexFragment extends BaseFragment<UserView,UserPresenter> implements UserView{

    @BindView(R.id.oivAlbum)
    TextView mOivAlbum;

    @BindView(R.id.tvName)
    TextView mTvName;

    @BindView(R.id.tvAccount)
    TextView mTvAccount;


    @Override
    protected UserPresenter createPresenter() {

        return new UserPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initData() {
        mPresenter.loadUserInfo();
    }

    @Override
    public void initListener() {

        mOivAlbum.setOnClickListener(v -> mPresenter.jumpWebActivity());

    }
    @Override
    public TextView getName() {
        return mTvName;
    }

    @Override
    public TextView getAccount() {
        return mTvAccount;
    }




}
