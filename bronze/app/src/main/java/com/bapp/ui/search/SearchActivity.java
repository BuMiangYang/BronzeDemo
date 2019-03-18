package com.bapp.ui.search;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bapp.R;
import com.bapp.base.BaseActivity;
import com.bapp.base.BasePresenter;
import com.bapp.utlis.ViewAnimUtils;
import com.bapp.view.ClearEditText;
import com.classic.common.MultipleStatusView;

import butterknife.BindView;

/**
 * @author bmy
 * des: 搜索界面
 */
public class SearchActivity extends BaseActivity {


    @BindView(R.id.fab_circle)
    FloatingActionButton mFabCircle;
    @BindView(R.id.et_search_view)
    ClearEditText mEtSearchView;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.rel_search_view)
    RelativeLayout mRelSearchView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title_tip)
    TextView mTvTitleTip;
    @BindView(R.id.tv_hot_search_words)
    TextView mTvHotSearchWords;
    @BindView(R.id.mRecyclerView_hot)
    RecyclerView mMRecyclerViewHot;
    @BindView(R.id.layout_hot_words)
    LinearLayout mLayoutHotWords;
    @BindView(R.id.tv_search_count)
    TextView mTvSearchCount;

    @BindView(R.id.mRecyclerView_result)
    RecyclerView mMRecyclerViewResult;

    @BindView(R.id.multipleStatusView)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.layout_content_result)
    LinearLayout mLayoutContentResult;
    @BindView(R.id.rel_container)
    LinearLayout mRelContainer;
    @BindView(R.id.rel_frame)
    RelativeLayout mRelFrame;

    public static void runActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    /**
     * 开始请求
     */
    @Override
    protected void start() {

    }

    /**
     * 绑定布局ID
     *
     * @return
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 入场动画
            setUpEnterAnimation();
            // 退场动画
            setUpExitAnimation();
        } else {
            setUpView();
        }
    }

    @Override
    public void initView() {
        super.initView();

        mMRecyclerViewResult.setLayoutManager(new LinearLayoutManager(this));

        mTvCancel.setOnClickListener(view -> onBackPressed());

//        StatusBarUtil.darkMode(this);
//        StatusBarUtil.setPaddingSmart(this, toolbar);

    }

    // 返回事件
    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimUtils.animateRevealHide(
                    this, mRelFrame,
                    mFabCircle.getWidth() / 2, R.color.backgroundColor,
                    new ViewAnimUtils.OnRevealAnimationListener() {
                        @Override
                        public void onRevealHide() {
                            defaultBackPressed();
                        }

                        @Override
                        public void onRevealShow() {

                        }
                    });
        } else {
            defaultBackPressed();
        }
    }

    // 默认回退
    private void defaultBackPressed() {
//        closeSoftKeyboard();
        super.onBackPressed();
    }

    /**
     * 进场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.arc_motion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    /**
     * 退场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(300);
    }

    private void setUpView() {
        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animation.setDuration(300);
        mRelContainer.startAnimation(animation);
        mRelContainer.setVisibility(View.VISIBLE);
        //打开软键盘
        //openKeyBord(et_search_view, applicationContext);
    }

    /**
     * 展示动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {

        ViewAnimUtils.animateRevealShow(
                this, mRelFrame,
                mFabCircle.getWidth() / 2, R.color.backgroundColor,
                new ViewAnimUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        setUpView();
                    }
                });
    }
}
