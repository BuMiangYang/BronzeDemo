package dictation.appcode.com.bapp.ui.read;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import dictation.appcode.com.bapp.adapter.ReadIndexAdapter;
import dictation.appcode.com.bapp.base.BaseActivity;
import dictation.appcode.com.bapp.base.BasePresenter;
import dictation.appcode.com.bapp.entity.WorkInfo;

/**
 * 描述：此类创建适配/recycleView 填充
 */

public class ReadPresenter extends BasePresenter<ReadView> implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {


    private ReadIndexAdapter mReadIndexAdapter;

    private static final int PAGE_SIZE = 6;
    private int mNextRequestPage = 1;

    public ReadPresenter(BaseActivity context) {
        super(context);
    }

    /**
     * 加载数据
     */
    public void loadData() {

        setAdapter();

    }


    private void setAdapter() {

        if (mReadIndexAdapter == null) {


            // 初始化
            mReadIndexAdapter = new ReadIndexAdapter(loadWorkList());
            mReadIndexAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
            getView().getRecyclerView().setAdapter(mReadIndexAdapter);
            //加载更多
            mReadIndexAdapter.setOnLoadMoreListener(this, getView().getRecyclerView());
            // 下拉刷新
            getView().getSwipeRefrshlayout().setOnRefreshListener(this);
            //点击事件
            mReadIndexAdapter.setOnItemClickListener(this);


        }
    }

    /**
     * 获取填充数据
     *
     * @return
     */
    private List<WorkInfo> loadWorkList() {

        List<WorkInfo> workInfos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            WorkInfo workInfo = new WorkInfo();
            workInfo.title = "文章标题" + (i + 1);
            workInfo.description = "文章描述" + (i + 1);

            workInfos.add(workInfo);
        }

        return workInfos;
    }

    @Override
    public void onLoadMoreRequested() {

        setData(false, mNextRequestPage == 3 ? null :loadWorkList());

    }


    @Override
    public void onRefresh() {

        mNextRequestPage = 1;
        mReadIndexAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载

        setData(true, loadWorkList());

        mReadIndexAdapter.setEnableLoadMore(true);
        getView().getSwipeRefrshlayout().setRefreshing(false);
    }

    private void setData(boolean isRefresh, List data) {

        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mReadIndexAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mReadIndexAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mReadIndexAdapter.loadMoreEnd(isRefresh);
        } else {
            mReadIndexAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        ReadDetailActivity.runActivity(mContext);
    }
}

