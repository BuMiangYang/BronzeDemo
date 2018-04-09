package dictation.appcode.com.bapp.ui.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import dictation.appcode.com.bapp.adapter.AndroidFragmentAdapter;
import dictation.appcode.com.bapp.base.BaseActivity;
import dictation.appcode.com.bapp.base.BasePresenter;
import dictation.appcode.com.bapp.entity.WorkInfo;


/**
 * 描述：
 */

public class AndroidPresenter extends BasePresenter<AndroidView> implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {


    public AndroidPresenter(BaseActivity context) {
        super(context);
    }


    private AndroidFragmentAdapter mReadIndexAdapter;
    private static final int PAGE_SIZE = 9;
    private int mNextRequestPage = 1;

    /**
     * 加载数据
     */
    public void loadData() {
        setAdapter();
    }


    private void setAdapter() {

        // 初始化
        mReadIndexAdapter = new AndroidFragmentAdapter(null);
        mReadIndexAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        getView().getRecyclerView().setAdapter(mReadIndexAdapter);
        //加载更多
        mReadIndexAdapter.setOnLoadMoreListener(this, getView().getRecyclerView());
        // 下拉刷新
        getView().getSwipeRefresh().setOnRefreshListener(this);
        //点击事件
        mReadIndexAdapter.setOnItemClickListener(this);

        onRefresh();


    }

    /**
     * 获取填充数据
     *
     * @return
     */

    private void loadWorkList() {
        // 暂不添加正式网络请求
        List<WorkInfo> workInfoList = new ArrayList<>();
        WorkInfo workInfo;
        for (int i = 0; i < 9; i++) {
            workInfo = new WorkInfo();
            workInfo.description = "描述" + (i + 1);
            workInfo.title = "标题" + (i + 1);
            workInfoList.add(workInfo);
        }
        setData(true, workInfoList);


    }

    @Override
    public void onLoadMoreRequested() {

        List<WorkInfo> workInfoList = new ArrayList<>();
        WorkInfo workInfo;
        for (int i = 0; i < 9; i++) {
            workInfo = new WorkInfo();
            workInfo.description = "描述" + (i + 1);
            workInfo.title = "标题" + (i + 1);
            workInfoList.add(workInfo);
        }
        setData(false, workInfoList);

    }


    @Override
    public void onRefresh() {

        Log.e("OKGO", "视频刷新");
        mNextRequestPage = 1;
        mReadIndexAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        loadWorkList();
        mReadIndexAdapter.setEnableLoadMore(true);
        getView().getSwipeRefresh().setRefreshing(false);
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

        Toast.makeText(mContext, "点击事件", Toast.LENGTH_LONG).show();

    }


}
