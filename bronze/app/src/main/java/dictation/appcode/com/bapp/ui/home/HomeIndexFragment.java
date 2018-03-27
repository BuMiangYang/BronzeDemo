package dictation.appcode.com.bapp.ui.home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import dictation.appcode.com.bapp.ui.MainActivity;
import dictation.appcode.com.bapp.R;
import dictation.appcode.com.bapp.adapter.HomeAdapter;
import dictation.appcode.com.bapp.base.BaseFragment;
import dictation.appcode.com.bapp.base.BasePresenter;

/**
 * 描述：
 */

public class HomeIndexFragment extends BaseFragment {

    private MainActivity mContext;

    private String[] mTitles = new String[]{"推荐", "连载", "专题"};

    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.home_viewpager) ViewPager mViewPager;

    private List<Fragment> mListFragments;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (MainActivity) context;
    }

    @Override
    public void initData() {

        for(String tab: mTitles){
            mTabLayout.addTab(mTabLayout.newTab().setText(tab));
        }

        mListFragments = new ArrayList<>();
        mListFragments.add(new AndroidFragment());
        mListFragments.add(new IosFragment());
        mListFragments.add(new WelfareFragment());



        HomeAdapter homeAdapter = new HomeAdapter(getChildFragmentManager(), mContext, mListFragments, mTitles);
        mViewPager.setAdapter(homeAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }
}
