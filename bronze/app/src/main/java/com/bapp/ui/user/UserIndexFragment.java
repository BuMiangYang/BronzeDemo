package com.bapp.ui.user;

import android.widget.ImageView;
import android.widget.TextView;

import com.bapp.R;
import com.bapp.base.BaseFragment;
import com.bapp.ui.MainActivity;

import butterknife.BindView;

/**
 * 描述：
 * @author bmy
 */

public class UserIndexFragment extends BaseFragment<UserView, UserPresenter> implements UserView {

    @BindView(R.id.oivAlbum)
    TextView mOivAlbum;

    @BindView(R.id.tvName)
    TextView mTvName;

    @BindView(R.id.tvAccount)
    TextView mTvAccount;

    @BindView(R.id.iv_search)
    ImageView mSearchView;


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

        mSearchView.setOnClickListener(view -> {

            mPresenter.jumpSearch();
        });

    }
    @Override
    public TextView getName() {
        return mTvName;
    }

    @Override
    public TextView getAccount() {
        return mTvAccount;
    }

    @Override
    public ImageView getSearch() {
        return mSearchView;
    }


}
