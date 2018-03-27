package dictation.appcode.com.bapp.ui.read;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import butterknife.BindView;
import dictation.appcode.com.bapp.R;
import dictation.appcode.com.bapp.base.BaseFragment;
import dictation.appcode.com.bapp.ui.MainActivity;

/**
 * 描述：占位 还没想好
 */

public class ReadIndexFragment extends BaseFragment<ReadView, ReadPresenter> implements ReadView {


    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;


    @Override
    protected ReadPresenter createPresenter() {
        return new ReadPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_read;
    }

    @Override
    public void initData() {

        mPresenter.loadData();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayout.VERTICAL);
        mRecycleView.setLayoutManager(manager);


    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecycleView;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefrshlayout() {
        return mSwipeRefresh;
    }

}
