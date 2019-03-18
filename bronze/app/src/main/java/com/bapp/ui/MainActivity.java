package com.bapp.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.bapp.R;
import com.bapp.adapter.MainAdapter;
import com.bapp.base.BaseActivity;
import com.bapp.ui.home.HomeIndexFragment;
import com.bapp.ui.read.ReadIndexFragment;
import com.bapp.ui.user.UserIndexFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页TAB 模块
 *
 * @author bmy
 */

public class MainActivity extends BaseActivity<MainView, MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    //底部导航
    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigation;


    private final int NAVIGATION_HOME = 0;
    private final int NAVIGATION_READ = 1;
    private final int NAVIGATION_ME = 2;

    private List<Fragment> mList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mVpMain.setCurrentItem(NAVIGATION_HOME);
                    return true;
                case R.id.navigation_read:
                    mVpMain.setCurrentItem(NAVIGATION_READ);
                    return true;
                case R.id.navigation_me:
                    mVpMain.setCurrentItem(NAVIGATION_ME);
                    return true;
            }
            return false;
        }

    };


    @Override
    public void initData() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 初始化Fragment

        mList = new ArrayList<>();
        mList.add(new HomeIndexFragment());
        mList.add(new ReadIndexFragment());
        mList.add(new UserIndexFragment());

        // 初始化适配

        MainAdapter mMainAdapter = new MainAdapter(getSupportFragmentManager(), this, mList);
        mVpMain.setAdapter(mMainAdapter);


    }

    /**
     * 开始请求
     */
    @Override
    protected void start() {

    }

    @Override
    public void initListener() {
        super.initListener();
        navigationView.setNavigationItemSelectedListener(this);
        mVpMain.addOnPageChangeListener(this);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * 绑定布局ID
     *
     * @return
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected MainPresenter createPresenter() {
        return null;
    }


    private boolean isPrepareFinish = false;

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!isPrepareFinish) {
            mVpMain.postDelayed(
                    () -> isPrepareFinish = false, 2000
            );
            isPrepareFinish = true;
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_jianshu) {

//            WebActivity.runActivity(MainActivity.this,"简书", Apis.JIANSHU);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case NAVIGATION_HOME:
                mBottomNavigation.setSelectedItemId(R.id.navigation_home);
                break;
            case NAVIGATION_READ:
                mBottomNavigation.setSelectedItemId(R.id.navigation_read);
                break;
            case NAVIGATION_ME:
                mBottomNavigation.setSelectedItemId(R.id.navigation_me);
                break;
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
