package dictation.appcode.com.bapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * 描述：
 *
 */

public abstract class BaseFragmentActivity<V ,T extends BaseFragmentPresenter<V>> extends FragmentActivity {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        
        mPresenter = createPresenter();
        if(null != mPresenter){
            mPresenter.attachView((V)this);
        }

        setContentView(provideContentViewId());

        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }

    public abstract int provideContentViewId() ;

    protected abstract T createPresenter();

    public void init() {
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }
}
