package dictation.appcode.com.bapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 描述: 主界面ViewPage适配器
 */
public class MainAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mList;

    public MainAdapter(FragmentManager fm, Context mContext, List<Fragment> mList) {
        super(fm);
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }


    public void setmList(List<Fragment> mList) {
        this.mList = mList;
    }
}
