package dictation.appcode.com.bapp.ui.read;

import dictation.appcode.com.bapp.R;
import dictation.appcode.com.bapp.base.BaseActivity;

public class ReadDetailActivity extends BaseActivity<DetailView, DetailPresenter> implements DetailView {

    /**
     * 绑定布局ID
     *
     * @return
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_read_detail;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     */
    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter(this);
    }
}
