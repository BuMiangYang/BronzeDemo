package dictation.appcode.com.bapp.ui.home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Pair;

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


    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.home_viewpager) ViewPager mViewPager;

    private List<Pair<String, Fragment>> mListFragments;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (MainActivity) context;
    }

    @Override
    public void initData() {

        mListFragments = new ArrayList<>();
        mListFragments.add(new Pair<String, Fragment>("推荐", new AndroidFragment()));
        mListFragments.add(new Pair<String, Fragment>("连载", new TestFragment()));
        mListFragments.add(new Pair<String, Fragment>("专题", new IosFragment()));
        mListFragments.add(new Pair<String, Fragment>("未知", new WelfareFragment()));

        HomeAdapter homeAdapter = new HomeAdapter(getChildFragmentManager(), mListFragments);
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
